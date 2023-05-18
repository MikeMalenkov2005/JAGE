package com.github.MikeMalenkov2005;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window implements Runnable {
    private static boolean initialized = false;

    public static void init(PrintStream log) {
        GLFWErrorCallback.createPrint(log).set();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
        initialized = true;
    }

    public static void terminate() {
        glfwTerminate();
        glfwSetErrorCallback(null);
        initialized = false;
    }

    public static int loadBuffer(int usage, byte... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, ByteBuffer.wrap(data), usage);
        return id;
    }

    public static int loadBuffer(int usage, short... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    public static int loadBuffer(int usage, int... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    public static int loadBuffer(int usage, long... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    public static int loadBuffer(int usage, float... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    public static int loadBuffer(int usage, double... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    public static void deleteBuffers(int... buffers) {
        glDeleteBuffers(buffers);
    }

    public static int compileShader(int type, String src) {
        int id = glCreateShader(type);
        glShaderSource(id, src);

        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
            throw new IllegalStateException("Error: Shader - " + glGetShaderInfoLog(id));

        return id;
    }

    public static void deleteShaders(int... shaders) {
        for (int shader : shaders) {
            glDeleteShader(shader);
        }
    }

    public static int linkShaderProgram(int... shaders) {
        int id = glCreateProgram();
        for (int shader : shaders)
            glAttachShader(id, shader);

        glLinkProgram(id);
        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE)
            throw new IllegalStateException("Error: Linking Program - " + glGetProgramInfoLog(id));

        glValidateProgram(id);
        if (glGetProgrami(id, GL_VALIDATE_STATUS) == GL_FALSE)
            throw new IllegalStateException("Error: Validating Program - " + glGetProgramInfoLog(id));

        return id;
    }

    public static void deleteShaderPrograms(int... programs) {
        for (int program : programs) {
            glDeleteProgram(program);
        }
    }

    public static int createTexture1D(int width, int levels, int format, int wrapMode, int filter) {
        int id = glCreateTextures(GL_TEXTURE_1D);
        glTextureStorage1D(id, levels, format, width);
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode);
        return id;
    }

    public static int createTexture2D(int width, int height, int levels, int format, int wrapMode, int filter) {
        int id = glCreateTextures(GL_TEXTURE_2D);
        glTextureStorage2D(id, levels, format, width, height);
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode);
        glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrapMode);
        return id;
    }

    public static int createTexture3D(int width, int height, int depth, int levels, int format, int wrapMode, int filter) {
        int id = glCreateTextures(GL_TEXTURE_3D);
        glTextureStorage3D(id, levels, format, width, height, depth);
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode);
        glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrapMode);
        return id;
    }

    public static int loadTexture(BufferedImage image, int wrapMode, int filter) {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
        for (int y = image.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }
        buffer.flip();
        int id = createTexture2D(image.getWidth(), image.getHeight(), 1, GL_RGBA8, wrapMode, filter);
        glTextureSubImage2D(id, 0, 0, 0, image.getWidth(), image.getHeight(), GL_RGBA, GL_UNSIGNED_BYTE, MemoryUtil.memAddress(buffer));
        return id;
    }

    public static void deleteTextures(int... textures) {
        glDeleteTextures(textures);
    }

    private final long window;
    private final int[] realWidth = new int[1], realHeight = new int[1];
    private long monitor;
    private int width, height, xPos, yPos;
    private double x, y;
    private WindowContent content;

    public Window(int width, int height, String title, PrintStream log) {
        if (!initialized)
            init(log);

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        xPos = 60;
        yPos = 90;
        monitor = 0;
        window = glfwCreateWindow(width, height, title, monitor, 0);
        if (window == NULL)
            throw new IllegalStateException("Could not create a GLFW window");

        this.width = width;
        this.height = height;
        updateWindowPosition();
        glfwGetFramebufferSize(window, realWidth, realHeight);

        glfwSetCursorPosCallback(window, this::mousePosCallback);
        glfwSetMouseButtonCallback(window, this::mouseButtonCallback);
        glfwSetScrollCallback(window, this::mouseScrollCallback);
        glfwSetKeyCallback(window, this::keyCallback);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        // IS REQUIRED FOR SOME REASON
        GL.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void run() {
        while (!glfwWindowShouldClose(window)) {
            glClearColor(0, 0, 0, 1);
            glClear(GL_COLOR_BUFFER_BIT);

            if (content != null)
                content.draw(this);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }

    public void close() {
        glfwSetWindowShouldClose(window, true);
    }

    public long getMonitor() {
        return monitor;
    }

    public void setMonitor(long monitor) {
        if (this.monitor == 0)
            updateWindowPosition();
        this.monitor = monitor;
        if (monitor != 0) {
            GLFWVidMode vidMode = glfwGetVideoMode(monitor);
            if (vidMode != null) {
                glfwSetWindowMonitor(window, monitor, 0, 0, vidMode.width(), vidMode.height(), vidMode.refreshRate());
                glViewport(0, 0, vidMode.width(), vidMode.height());
                glfwGetFramebufferSize(window, realWidth, realHeight);
            }
        } else {
            glfwSetWindowMonitor(window, 0, xPos, yPos, width, height, 0);
            glViewport(0, 0, width, height);
            glfwGetFramebufferSize(window, realWidth, realHeight);
        }
    }

    public int getWindowWidth() {
        return width;
    }

    public int getWindowHeight() {
        return height;
    }

    public void setWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
        if (monitor == 0) {
            updateWindowPosition();
            glfwSetWindowMonitor(window, 0, xPos, yPos, width, height, 0);
            glViewport(0, 0, width, height);
            glfwGetFramebufferSize(window, realWidth, realHeight);
        }
    }

    public int getRealWidth() {
        return realWidth[0];
    }

    public int getRealHeight() {
        return realHeight[0];
    }

    public WindowContent getContent() {
        return content;
    }

    public void setContent(WindowContent windowContent) {
        this.content = windowContent;
    }

    private void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (content != null) {
            switch (action) {
                case GLFW_RELEASE -> content.keyRelease(this, x, y, key, mods);
                case GLFW_PRESS -> content.keyPress(this, x, y, key, mods);
                case GLFW_REPEAT -> content.keyHold(this, x, y, key, mods);
            }
        }
    }

    private void mouseButtonCallback(long window, int button, int action, int mods) {
        if (content != null) {
            switch (action) {
                case GLFW_RELEASE -> content.mouseRelease(this, x, y, button, mods);
                case GLFW_PRESS -> content.mousePress(this, x, y, button, mods);
                case GLFW_REPEAT -> content.mouseHold(this, x, y, button, mods);
            }
        }
    }

    private void mouseScrollCallback(long window, double xScroll, double yScroll) {
        if (content != null) content.mouseScroll(this, x, y, xScroll, yScroll);
    }

    private void mousePosCallback(long window, double x, double y) {
        double dx = x - this.x;
        double dy = y - this.y;
        this.x = x;
        this.y = y;
        if (content != null) content.mouseMove(this, x, y, dx, dy);
    }

    private void updateWindowPosition() {
        int[] xp = new int[1];
        int[] yp = new int[1];
        glfwGetWindowPos(window, xp, yp);
        xPos = xp[0];
        yPos = yp[0];
    }
}
