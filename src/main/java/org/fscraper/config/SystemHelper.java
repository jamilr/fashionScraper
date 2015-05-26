package org.fscraper.config;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

/**
 * @author Jamil Rzayev March, 2015
 * */

public class SystemHelper {

    private static Logger logger = Logger.getLogger("SystemHelper");

    public static Date parseDate(String dateValue) {

        try {
            return new SimpleDateFormat("dd-mm-yyyy").parse(dateValue);
        } catch (ParseException parseEx){
            logger.error(parseEx.getMessage());
            return null;
        }
    }

    public static Method reflectMethod(Class<?> targetClass, String methodName, Class<?>[] parameterTypes) throws Exception {
        Method method = targetClass.getDeclaredMethod(methodName, parameterTypes);
        return method;
    }

    public static <T> String getFilePath(Class<T> clazz, String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty())
            return null;

        URL fileUrl = clazz.getResource("/" + fileName);

        if (fileUrl == null)
            return null;

        String fullQualifiedPath = URLDecoder.decode(fileUrl.getPath(), "utf-8");
        return fullQualifiedPath;
    }

    public static String getFilePath(URL relativePath) {

        File xmlFile = FileUtils.toFile(relativePath);
        String filePath = xmlFile.getPath();

        return filePath;
    }

    public static void writeImageToFile(InputStream inputStream, String toPath) throws IOException {
        BufferedImage image = null;
        image = ImageIO.read(inputStream);
        ImageIO.write(image, "jpg", new File(toPath));
    }

    public static void writeImageBytesToFile(byte[] imageBytes, String toPath) throws IOException {
        // convert byte array back to BufferedImage
        InputStream in = new ByteArrayInputStream(imageBytes);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        ImageIO.write(bImageFromConvert, "jpg", new File(toPath));
    }

    public static byte[] writeImageToBuffer(InputStream inputStream) throws IOException {
        BufferedImage image = null;
        byte[] imageInByte = new byte[0];

        image = ImageIO.read(inputStream);
        imageInByte = convertImageToBytes(image);

        return imageInByte;
    }

    /**
     * Convert the image to bytes from file name.
     * */
    public static byte[] readImageFromFile(String fromPath) throws IOException {
        BufferedImage image = null;
        byte[] imageInByte = new byte[0];

        image = ImageIO.read(new File(fromPath));
        imageInByte = convertImageToBytes(image);

        return imageInByte;
    }

    /**
     * Concatenate arbitrary number of image bytes
     * */
    public static byte[] concatenateBytes(byte[] ... byteArrays) {
        // Determine the length of the result array
        int totalLength = 0;
        for (int i = 0; i < byteArrays.length; i++) {
            totalLength += byteArrays[i].length;
        }

        // create the result array
        byte[] result = new byte[totalLength];

        // copy the source arrays into the result array
        int currentIndex = 0;
        for (int i = 0; i < byteArrays.length; i++) {
            System.arraycopy(byteArrays[i], 0, result, currentIndex, byteArrays[i].length);
            currentIndex += byteArrays[i].length;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] arrayMerge(T[]... arrays) {
        // Determine required size of new array
        int count = 0;

        for (T[] array : arrays) {
            count += array.length;
        }

        // create new array of required class
        T[] mergedArray = (T[]) Array.newInstance(arrays[0][0].getClass(), count);

        // Merge each array into new array
        int start = 0;

        for (T[] array : arrays) {
            System.arraycopy(array, 0, mergedArray, start, array.length);
            start += array.length;
        }

        return (T[]) mergedArray;
    }


    public static void createFile(File file, int fileSize, boolean recreate) throws IOException {
        FileOutputStream fos = null;

        try {
            byte[] bufferArray = new byte[fileSize];
            String fileName = file.getName();

            if (recreate)
                if (!file.delete()) {
                    logger.info("Delete file " + fileName + " failed");
                    throw new IOException("Delete file " + fileName + " failed");
                }

            if (file.createNewFile())
                logger.info("New file " + fileName + " created successfully");
            else {
                logger.info("Create file " + fileName + " failed");
                throw new IOException("Create file " + fileName + " failed");
            }

            fos = new FileOutputStream(file);

            // create 32KB file
            fos.write(bufferArray);
            fos.flush();
        } finally {
            if (fos != null)
                fos.close();
        }
    }

    public static void createFolder(String path) throws IOException {
        File folder = new File(path);

        if (!folder.exists())
            if (!folder.mkdirs()) {
                logger.error("Cannot create folder " + path);
                throw new IOException("Cannot create folder " + path);
            }
    }

    public static void deleteFile(String path, String filename) throws IOException {
        File file = new File(path + "/" + filename);

        if (file.delete())
            logger.info("Model " + filename + " was deleted successfully");
        else {
            logger.error("Model " + filename + " failed to be deleted");
            throw new IOException("Model " + filename + " failed to be deleted");
        }
    }

    public static byte[] convertStringToByte(String input) {
        return input == null ? new byte[0] : input.getBytes();
    }

    public static int convertByteToInt(byte[] data) {
        if (data == null)
            return 0;

        ByteBuffer buffer = ByteBuffer.wrap(data);
        return buffer.getInt();
    }

    public static String convertByteToString(byte[] data) {
        if (data == null)
            return null;

        if (data.length == 0)
            return null;

        return new String(data);
    }

    ///
    /// To compare the binary data from SCW, CSCW and etc
    ///
    public static boolean compareBinaryData(byte[] testData, byte[] originalData) throws IOException {
        return Arrays.equals(testData, originalData);
    }

    public static String int2String(int integer) {
        return Integer.toString(integer);
    }

    public static byte[] getHashKey(String input) {

        byte[] bytesOfMessage = null, digestMessage = null;
        ByteBuffer buffer = ByteBuffer.allocate(16);

        if (input == null) {
            buffer.putInt(0, 0);
            return buffer.array();
        }

        try {
            bytesOfMessage = input.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            digestMessage = md.digest(bytesOfMessage);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return digestMessage;
    }

    /**
     * To copy the range of the data bytes from the data.
     *
     * @param data 	- The array from which a range is to be copied
     * @param from 	- The initial index of the range to be copied, inclusive
     * @param to 	- The final index of the range to be copied, exclusive. (This index may lie outside the array.)
     *
     * @return A byte buffer.
     *
     * @author Tzyy Tong
     * @since 01-03-2013
     * */
    public static byte[] copyBytes(byte[] data, int from, int to) {
        return Arrays.copyOfRange(data, from, to);
    }

    public static byte[] swapBytes(byte[] data) {
        int totalLength = data.length;
        byte[] buffer = new byte[totalLength];

        for (int i=0; i<totalLength; i++)
            buffer[i] = data[totalLength-1-i];

        return buffer;
    }

    private static byte[] convertImageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream outStream = null;
        byte[] imageInByte = new byte[0];

        // Convert BufferedImage to byte array
        outStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outStream);
        outStream.flush();
        imageInByte = outStream.toByteArray();
        outStream.close();

        return imageInByte;
    }

}
