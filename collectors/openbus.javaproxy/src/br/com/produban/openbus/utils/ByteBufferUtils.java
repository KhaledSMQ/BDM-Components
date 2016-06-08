package br.com.produban.openbus.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferUtils {

    public static String parseContent(ByteBuffer buffer, int offset, int length) throws IOException {
//        InputStream inputStream = new ByteBufferBackedInputStream(buffer);
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
//        BufferedReader bufferedReader = null;
        byte[] chunk = new byte[length];
        char[] start = new char[length];
        try {
//            bufferedReader = new BufferedReader(inputStreamReader);
//            bufferedReader.read(start, offset, length);
            buffer.get(chunk,offset,length);
            return new String(chunk, StandardCharsets.ISO_8859_1);
//            return String.valueOf(start);
        } finally {
            buffer.rewind();
//            bufferedReader.close();
        }
    }

}
