package com.projeto.upload.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.projeto.upload.model.BannerFile;
import com.projeto.upload.util.ArquivoUtil;

//http://www.devmedia.com.br/use-remotecommand-para-comunicacao-entre-javascript-e-jsf/33107

@ManagedBean
public class UploadAvancadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<BannerFile> arquivos = new ArrayList<>();

	private StreamedContent image;

	private StreamedContent imageBanner1;
	private StreamedContent imageBanner2;
	private File fileBanner;

	@PostConstruct
	public void postConstruct() {
		List<File> files = new ArrayList<>(ArquivoUtil.listar());

//		System.out.println("Arquivos count >> :" + files.size());
		try {
			carregaImagens(files);
		} catch (IOException e) {
		}
	}

	public void carregaImagens(List<File> files) throws IOException {
		for (File file : files) {
			arquivos.add(new BannerFile(file));
		}
	}

	public void upload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();

		try {
			File arquivo = ArquivoUtil.escrever(uploadedFile.getFileName(), uploadedFile.getContents());

//			image = sc(arquivo);
			
			fileBanner = arquivo;

			arquivos.add(new BannerFile(arquivo));
			
//			System.out.println("# CanonicalPath >> :" + arquivo.getCanonicalPath());
//			System.out.println("# CanonicalFile >> :" + arquivo.getCanonicalFile());
//			System.out.println("# AbsoluteFile >> :" + arquivo.getAbsoluteFile());
//			System.out.println("# AbsolutePath >> :" + arquivo.getAbsolutePath());
//			System.out.println("# Path >> :" + arquivo.getPath());
//			System.out.println("# ParentFile >> :" + arquivo.getParentFile());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Upload completo", "O arquivo " + arquivo.getName() + " foi salvo!"));

		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}

	public List<BannerFile> getArquivos() {
		return arquivos;
	}

	public StreamedContent sc(File file) throws IOException {
		InputStream stream;
		try {
			stream = new FileInputStream(file);
			image = new DefaultStreamedContent(stream, Files.probeContentType(file.toPath()), file.getName());
		} catch (FileNotFoundException e) {
		}
		return image;
	}

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String pid = context.getExternalContext().getRequestParameterMap().get("pid");
			String name = context.getExternalContext().getRequestParameterMap().get("name");
//			System.out.println("# Pid >> :" + pid);
//			System.out.println("# Name >> :" + name);

			if (!name.startsWith("thumbnail")) {
				name = "thumbnail." + name;
			}

			String caminho = pid + File.separator + name;

//			System.out.println("# Caminho >> :" + caminho);

			// Path path = Paths.get(pid);
			// byte[] data = Files.readAllBytes(path);
			byte[] data = Files.readAllBytes(new File(caminho).toPath());

			return new DefaultStreamedContent(new ByteArrayInputStream(data));
		}
}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public StreamedContent getImageBanner1() throws IOException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		String caminho = context.getExternalContext().getRequestParameterMap().get("idBanner");

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		
		} else if(caminho != null && !caminho.isEmpty()  ){
			System.out.println("# Caminho  >> :" + caminho);
			byte[] data = Files.readAllBytes(new File(caminho).toPath());

			return new DefaultStreamedContent(new ByteArrayInputStream(data));
		}
		
		return imageBanner1;
	}

	public void setImageBanner1(StreamedContent imageBanner1) {
		this.imageBanner1 = imageBanner1;
	}

	public StreamedContent getImageBanner2() {
		return imageBanner2;
	}

	public void setImageBanner2(StreamedContent imageBanner2) {
		this.imageBanner2 = imageBanner2;
	}

	public File getFileBanner() {
		return fileBanner;
	}

	public void setFileBanner(File fileBanner) {
		this.fileBanner = fileBanner;
	}
	
}