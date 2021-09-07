package moviebuddy.util;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

import moviebuddy.ApplicationException;

public class FileSystemUtils {
	
	public static URI checkFileSystem(URI uri) {
		if("jar".equalsIgnoreCase(uri.getScheme())){
		    for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
		        if (provider.getScheme().equalsIgnoreCase("jar")) {
		            try {
		                provider.getFileSystem(uri);
		            } catch (FileSystemNotFoundException ignore) {
		                try {
							provider.newFileSystem(uri, Collections.emptyMap());
						} catch (IOException error) {
							throw new ApplicationException("failed to initialize file system.", error);
						}
		            }
		        }
		    }
		}
		return uri;
	}
	
}
