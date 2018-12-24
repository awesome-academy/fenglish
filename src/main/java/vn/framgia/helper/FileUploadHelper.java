package vn.framgia.helper;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

public class FileUploadHelper {

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	private Cloudinary getCloudinary() {
		String cloudName = messageSource.getMessage("cloud_name", null, LocaleContextHolder.getLocale());
		String apiKey = messageSource.getMessage("api_key", null, LocaleContextHolder.getLocale());
		String apiSecret = messageSource.getMessage("api_secret", null, LocaleContextHolder.getLocale());

		return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
	}

	public String upFile(MultipartFile multipart) {
		File file = new File(multipart.getOriginalFilename());

		try {
			file.createNewFile();

			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipart.getBytes());
			fos.close();

			String publicId = getCloudinary().uploader().upload(file, ObjectUtils.emptyMap()).get("public_id")
					.toString();
			return loadFile(publicId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String loadFile(String publicId) {
		try {
			return getCloudinary().url().format("jpg")
					.transformation(new Transformation().width(512).height(512).crop("fit")).generate(publicId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
