package com.github.MikeMalenkov2005.jage;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Deprecated
    public static int loadBuffer(int usage, byte... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, ByteBuffer.wrap(data), usage);
        return id;
    }

    @Deprecated
    public static int loadBuffer(int usage, short... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    @Deprecated
    public static int loadBuffer(int usage, int... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    @Deprecated
    public static int loadBuffer(int usage, long... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    @Deprecated
    public static int loadBuffer(int usage, float... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    @Deprecated
    public static int loadBuffer(int usage, double... data) {
        int id = glCreateBuffers();
        glNamedBufferData(id, data, usage);
        return id;
    }

    @Deprecated
    public static void deleteBuffers(int... buffers) {
        glDeleteBuffers(buffers);
    }

    @Deprecated
    public static int compileShader(int type, String src) {
        int id = glCreateShader(type);
        glShaderSource(id, src);

        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
            throw new IllegalStateException("Error: Shader - " + glGetShaderInfoLog(id));

        return id;
    }

    @Deprecated
    public static void deleteShaders(int... shaders) {
        for (int shader : shaders) {
            glDeleteShader(shader);
        }
    }

    @Deprecated
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

    @Deprecated
    public static void deleteShaderPrograms(int... programs) {
        for (int program : programs) {
            glDeleteProgram(program);
        }
    }

    @Deprecated
    public static int createTexture1D(int width, int levels, int format, int wrapMode, int filter) {
        int id = glCreateTextures(GL_TEXTURE_1D);
        glTextureStorage1D(id, levels, format, width);
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode);
        return id;
    }

    @Deprecated
    public static int createTexture2D(int width, int height, int levels, int format, int wrapMode, int filter) {
        int id = glCreateTextures(GL_TEXTURE_2D);
        glTextureStorage2D(id, levels, format, width, height);
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode);
        glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrapMode);
        return id;
    }

    @Deprecated
    public static int createTexture3D(int width, int height, int depth, int levels, int format, int wrapMode, int filter) {
        int id = glCreateTextures(GL_TEXTURE_3D);
        glTextureStorage3D(id, levels, format, width, height, depth);
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filter);
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode);
        glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrapMode);
        glTextureParameteri(id, GL_TEXTURE_WRAP_R, wrapMode);
        return id;
    }

    @Deprecated
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

    public static long getPrimaryMonitor() {
        return glfwGetPrimaryMonitor();
    }

    public static long getMonitorByName(String name) {
        PointerBuffer monitors = Objects.requireNonNull(glfwGetMonitors());
        for (long monitor = monitors.get(); monitors.hasRemaining(); monitor = monitors.get()) {
            if (name.equals(glfwGetMonitorName(monitor))) {
                return monitor;
            }
        }
        return 0;
    }

    public Set<String> getMonitorNames() {
        Set<String> result = new HashSet<>();
        PointerBuffer monitors = Objects.requireNonNull(glfwGetMonitors());
        for (long monitor = monitors.get(); monitors.hasRemaining(); monitor = monitors.get()) {
            result.add(glfwGetMonitorName(monitor));
        }
        return result;
    }

    @Deprecated
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
        glfwSetCharCallback(window, this::charCallback);

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
                case GLFW_RELEASE -> content.release(this, x, y, getKeyName(key), mods);
                case GLFW_PRESS -> content.press(this, x, y, getKeyName(key), mods);
                case GLFW_REPEAT -> content.hold(this, x, y, getKeyName(key), mods);
            }
        }
    }

    private void charCallback(long window, int codePoint) {
        if (content != null) content.print(this, x, y, new String(Character.toChars(codePoint)));
    }

    private void mouseButtonCallback(long window, int button, int action, int mods) {
        if (content != null) {
            switch (action) {
                case GLFW_RELEASE -> content.release(this, x, y, "Mouse" + button, mods);
                case GLFW_PRESS -> content.press(this, x, y, "Mouse" + button, mods);
                case GLFW_REPEAT -> content.hold(this, x, y, "Mouse" + button, mods);
            }
        }
    }

    private void mouseScrollCallback(long window, double xScroll, double yScroll) {
        if (content != null) content.scroll(this, x, y, xScroll, yScroll);
    }

    private void mousePosCallback(long window, double x, double y) {
        double dx = x - this.x;
        double dy = y - this.y;
        this.x = x;
        this.y = y;
        if (content != null) content.move(this, x, y, dx, dy);
    }

    private void updateWindowPosition() {
        int[] xp = new int[1];
        int[] yp = new int[1];
        glfwGetWindowPos(window, xp, yp);
        xPos = xp[0];
        yPos = yp[0];
    }

    private static String getKeyName(int key) {
        return switch (key) {
            case 32 -> "Space";
            case 39 -> "Apostrophe";
            case 44 -> "Comma";
            case 45 -> "Minus";
            case 46 -> "Period";
            case 47 -> "Slash";
            case 48 -> "Num0";
            case 49 -> "Num1";
            case 50 -> "Num2";
            case 51 -> "Num3";
            case 52 -> "Num4";
            case 53 -> "Num5";
            case 54 -> "Num6";
            case 55 -> "Num7";
            case 56 -> "Num8";
            case 57 -> "Num9";
            case 59 -> "Semicolon";
            case 61 -> "Equal";
            case 65 -> "A";
            case 66 -> "B";
            case 67 -> "C";
            case 68 -> "D";
            case 69 -> "E";
            case 70 -> "F";
            case 71 -> "G";
            case 72 -> "H";
            case 73 -> "I";
            case 74 -> "J";
            case 75 -> "K";
            case 76 -> "L";
            case 77 -> "M";
            case 78 -> "N";
            case 79 -> "O";
            case 80 -> "P";
            case 81 -> "Q";
            case 82 -> "R";
            case 83 -> "S";
            case 84 -> "T";
            case 85 -> "U";
            case 86 -> "V";
            case 87 -> "W";
            case 88 -> "X";
            case 89 -> "Y";
            case 90 -> "Z";
            case 91 -> "LeftBracket";
            case 92 -> "Backslash";
            case 93 -> "RightBracket";
            case 96 -> "GraveAccent";
            case 161 -> "World1";
            case 162 -> "World2";
            case 256 -> "Escape";
            case 257 -> "Enter";
            case 258 -> "Tab";
            case 259 -> "Backspace";
            case 260 -> "Insert";
            case 261 -> "Delete";
            case 262 -> "Right";
            case 263 -> "Left";
            case 264 -> "Down";
            case 265 -> "Up";
            case 266 -> "PageUp";
            case 267 -> "PageDown";
            case 268 -> "Home";
            case 269 -> "End";
            case 280 -> "CapsLock";
            case 281 -> "ScrollLock";
            case 282 -> "NumLock";
            case 283 -> "PrintScreen";
            case 284 -> "Pause";
            case 290 -> "F1";
            case 291 -> "F2";
            case 292 -> "F3";
            case 293 -> "F4";
            case 294 -> "F5";
            case 295 -> "F6";
            case 296 -> "F7";
            case 297 -> "F8";
            case 298 -> "F9";
            case 299 -> "F10";
            case 300 -> "F11";
            case 301 -> "F12";
            case 302 -> "F13";
            case 303 -> "F14";
            case 304 -> "F15";
            case 305 -> "F16";
            case 306 -> "F17";
            case 307 -> "F18";
            case 308 -> "F19";
            case 309 -> "F20";
            case 310 -> "F21";
            case 311 -> "F22";
            case 312 -> "F23";
            case 313 -> "F24";
            case 314 -> "F25";
            case 320 -> "Keypad0";
            case 321 -> "Keypad1";
            case 322 -> "Keypad2";
            case 323 -> "Keypad3";
            case 324 -> "Keypad4";
            case 325 -> "Keypad5";
            case 326 -> "Keypad6";
            case 327 -> "Keypad7";
            case 328 -> "Keypad8";
            case 329 -> "Keypad9";
            case 330 -> "KeypadDecimal";
            case 331 -> "KeypadDivide";
            case 332 -> "KeypadMultiply";
            case 333 -> "KeypadSubtract";
            case 334 -> "KeypadAdd";
            case 335 -> "KeypadEnter";
            case 336 -> "KeypadEqual";
            case 340 -> "LeftShift";
            case 341 -> "LeftControl";
            case 342 -> "LeftAlt";
            case 343 -> "LeftSuper";
            case 344 -> "RightShift";
            case 345 -> "RightControl";
            case 346 -> "RightAlt";
            case 347 -> "RightSuper";
            case 348 -> "Menu";
            default -> "Unknown";
        };
    }
}
