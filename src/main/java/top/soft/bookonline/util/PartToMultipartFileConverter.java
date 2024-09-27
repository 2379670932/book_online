package top.soft.bookonline.util;

import jakarta.servlet.http.Part;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PartToMultipartFileConverter {

    public static MultipartFile convert(Part part) throws IOException {
        return new MultipartFile() {
            @Override
            public String getName() {
                return part.getName();
            }

            @Override
            public String getOriginalFilename() {
                return part.getSubmittedFileName();
            }

            @Override
            public String getContentType() {
                return part.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return part.getSize() == 0;
            }

            @Override
            public long getSize() {
                return part.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return org.apache.commons.io.IOUtils.toByteArray(part.getInputStream());
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return part.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                org.apache.commons.io.FileUtils.copyInputStreamToFile(part.getInputStream(), dest);
            }
        };
    }
}
