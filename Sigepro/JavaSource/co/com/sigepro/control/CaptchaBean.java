package co.com.sigepro.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.com.sigepro.control.util.FacesUtils;

import com.octo.captcha.service.image.ImageCaptchaService;

@Component("captchaBean")
@Scope("session")
public class CaptchaBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int AJUSTE_ALTO = 10;
	private static final int AJUSTE_ANCHO = 30;

	@Resource(name = "imageCaptchaService")
	private ImageCaptchaService imageCaptchaService;

	private String texto;
	private String textoGenerado;
	private int captchaWidth = 180;
	private int captchaHeight = 42;

	/**
	 * Paints Captcha Image
	 * 
	 * @param g2d
	 * @param obj
	 */
	public void paintCaptcha(Graphics2D g2d, Object obj) {
		textoGenerado = getRandomString();
		try {
			g2d.setClip(0, 0, captchaWidth, captchaHeight);
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, captchaWidth, captchaHeight);
			g2d.setColor(Color.decode("0x0A3D89"));
			g2d.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			g2d.drawString(textoGenerado, AJUSTE_ANCHO, captchaHeight
					- AJUSTE_ALTO);
		} catch (Exception ex) {
			FacesUtils.agregarMensajeError("error.captcha");
		}
	}

	protected BufferedImage generarCaptcha() {
		try {
			return imageCaptchaService.getImageChallengeForID(FacesUtils
					.getSession().getId());
		} catch (Exception ex) {
			FacesUtils.agregarMensajeError("error.captcha");
			return null;
		}
	}

	/** Generates Random Text for displaying on the image */
	public String getRandomString() {
		String str = "QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR";
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= 6; i++) {
			te = r.nextInt(62);
			sb.append(str.charAt(te));
		}

		return sb.toString();
	}

	public boolean validarCaptcha() {

		boolean validado = StringUtils.equalsIgnoreCase(textoGenerado, texto);
		texto = null;

		return validado;
	}

	public int getCaptchaWidth() {
		return captchaWidth;
	}

	public int getCaptchaHeight() {
		return captchaHeight;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setCaptchaWidth(int captchaWidth) {
		this.captchaWidth = captchaWidth;
	}

	public void setCaptchaHeight(int captchaHeight) {
		this.captchaHeight = captchaHeight;
	}
}
