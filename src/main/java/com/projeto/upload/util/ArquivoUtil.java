package com.projeto.upload.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class ArquivoUtil {

    public static File escrever(String name, byte[] contents) throws IOException {
        File file = new File(diretorioRaizParaArquivos(), name);
        
        OutputStream out = new FileOutputStream(file);
        out.write(contents);
        out.close();
        escreverThumb(file.toPath().toString());

        return file;
    }
    
    public static void escreverThumb(String path){
//    	System.out.println("# escreverThumb >> :" + path);
    	try {
			Thumbnails.of(path).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando thumbnail", e);
		}
    }

    public static List<File> listar() {
        File dir = diretorioRaizParaArquivos();

        return Arrays.asList(dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("thumbnail");
			}
		}));
    }

    public static java.io.File diretorioRaizParaArquivos() {
        File dir = new File(diretorioRaiz(), "arquivos");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
    
    public static File diretorioRaiz() {
        // Estamos utilizando um diretório dentro da pasta temporária. 
        // No seu projeto, imagino que queira mudar isso para algo como:
        // File dir = new File(System.getProperty("user.home"), "algaworks");
//        File dir = new File(System.getProperty("java.io.tmpdir"), "algaworks");
    	File dir = new File(System.getProperty("user.home"), "algaworks");
//        System.out.println("User profile : " + System.getProperty("user.home"));
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
}