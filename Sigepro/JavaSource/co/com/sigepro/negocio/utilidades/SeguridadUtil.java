package co.com.sigepro.negocio.utilidades;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Esta clase se utiliza para implementar métodos de seguridad
 * 
 * @author Carlos Mario Cadavid R
 * @version 1.0 21/06/2008
 * 
 */
public class SeguridadUtil {

	public static final byte[] secretKey = { 0x01, 0x02, 0x03, 0x04, 0x05,
			0x06, 0x07, 0x08, 0x7F, 0x1B, 0x0E, 0x13, 0x18, 0x1E, 0x17, 0x14,
			0x0B, 0x0F, 0x1F, 0x19 };

	/**
	 * Método para encriptar una cadena
	 * 
	 * @param clearText
	 * @param key
	 * @return
	 */
	public static String encriptar(String clearText, Key key) {
		String cipherTextB64 = "";

		try {
			// Necesitamos un cifrador
			Cipher cipher = Cipher.getInstance("DES");

			// Ciframos el texto en claro
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte cipherText[] = cipher.doFinal(clearText.getBytes());

			// Codificamos el texto cifrado en base 64
			BASE64Encoder base64encoder = new BASE64Encoder();
			cipherTextB64 = base64encoder.encode(cipherText);

		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		} catch (NoSuchPaddingException nspe) {
			nspe.printStackTrace();
		} catch (IllegalBlockSizeException ibse) {
			ibse.printStackTrace();
		} catch (BadPaddingException bpe) {
			bpe.printStackTrace();
		}

		// Retornamos el texto cifrado en BASE64
		return cipherTextB64;
	}

	/**
	 * Métoo para des-encriptar una cadena
	 * 
	 * @param cipherTextB64
	 * @param key
	 * @return
	 */
	public static String desencriptar(String cipherTextB64, Key key) {

		String clearText = "";

		try {
			// Necesitamos un cifrador
			Cipher cipher = Cipher.getInstance("DES");

			// La clave está codificada en base 64
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte cipherText[] = base64decoder.decodeBuffer(cipherTextB64);

			// Ciframos el texto en claro
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte bclearText[] = cipher.doFinal(cipherText);
			clearText = new String(bclearText);

		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (NoSuchPaddingException nspe) {
			nspe.printStackTrace();
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		} catch (IllegalBlockSizeException ibse) {
			ibse.printStackTrace();
		} catch (BadPaddingException bpe) {
			bpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return clearText;
	}

	/**
	 * Método que genera encripta una clave secreta con base en el algoritmo DES
	 * 
	 * @param claveRef
	 * @return
	 */
	public static SecretKey generarClave(byte[] claveRef) {
		SecretKey ClaveInicial = null;

		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
			ClaveInicial = skf.generateSecret(new DESKeySpec(claveRef));
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		} catch (InvalidKeySpecException ikse) {
			ikse.printStackTrace();
		}

		return ClaveInicial;
	}

}
