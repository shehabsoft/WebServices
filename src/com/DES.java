package com; 
import javax.swing.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random ;
		public class DES {
		byte[] skey = new byte[1000];
		String skeyString;
		static byte[] raw;
		String inputMessage,encryptedData,decryptedMessage;
		
		public DES() {
		try {
		generateSymmetricKey();
		
//		inputMessage=JOptionPane.showInputDialog(null,"Enter message to encrypt");
//		byte[] ibyte = inputMessage.getBytes();
//		byte[] ebyte=encrypt(ibyte);
//		String encryptedData = new String(ebyte);
//		System.out.println("Encrypted message "+encryptedData);
//		JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);
//		
//		byte[] dbyte= decrypt(ebyte);
//		String decryptedMessage = new String(dbyte);
//		System.out.println("Decrypted message "+decryptedMessage);
//		
//		JOptionPane.showMessageDialog(null,"Decrypted Data "+"\n"+decryptedMessage);
		}
		catch(Exception e) {
		System.out.println(e);
		}
		
		}
		void generateSymmetricKey() {
		try {
	
		String knum = String.valueOf(15);
		byte[] knumb = knum.getBytes();
		skey=getRawKey(knumb);
		skeyString="HomeBudget2016";
		System.out.println("DES Symmetric key = "+skeyString);
		}
		catch(Exception e) {
		System.out.println(e);
		}
		}
		private  byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("DES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		kgen.init(56, sr);
		SecretKey skey = kgen.generateKey();
		raw = skey.getEncoded();
		return raw;
		}
		public  byte[] encrypt( byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
		}
		
	
		public  byte[] decrypt( byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
		}
		
}