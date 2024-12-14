package com.culqi.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import org.json.JSONObject;

public class EncryptAESRSA {

	public String getJsonEncryptAESRSA(String input, String rsaPublicKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		
		JSONObject jsonObject = new JSONObject();
        
        SecretKey key = AESUtil.generateKey(256);
        //IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        GCMParameterSpec gcmParameterSpec = AESUtil.generateIvGCM();
        
        String encryptedData = AESUtil.encryptCulqiGCM(input, key, gcmParameterSpec);

		RSAUtil rsaUtil = new RSAUtil();

		String encryptedKey = rsaUtil.encriptarByte(key.getEncoded(), rsaPublicKey);
		String encryptedIv = rsaUtil.encriptarByte(gcmParameterSpec.getIV(), rsaPublicKey);
		
		jsonObject.put("encrypted_data", encryptedData);
        jsonObject.put("encrypted_key", encryptedKey);
        jsonObject.put("encrypted_iv", encryptedIv);

        return jsonObject.toString();	
	}
}
