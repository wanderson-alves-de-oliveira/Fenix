package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class MTLParser {


	public  static Vector<Material> loadMTL(InputStream file){
		BufferedReader reader=null;
		Vector<Material> materials=new Vector<Material>();
		String line;
		Material currentMtl=null;
		//try to open file
		reader = new BufferedReader(new InputStreamReader(file));
		if(reader!=null){
			try {//try to read lines of the file
				while((line = reader.readLine()) != null) {
					if(line.startsWith("newmtl")){
						if(currentMtl==null) {
							String mtName = line.split( " " )[1];
							currentMtl = new Material( mtName );
						}
					}
					else
					if(line.startsWith("Ka")){
						String[] str=line.split(" ");
						currentMtl.setAmbientColor(Float.parseFloat(str[1]), Float.parseFloat(str[2]), Float.parseFloat(str[3]));
					}
					else
					if(line.startsWith("Kd")){
						String[] str=line.split(" ");
						currentMtl.setDiffuseColor(Float.parseFloat(str[1]), Float.parseFloat(str[2]), Float.parseFloat(str[3]));
					}
					else
					if(line.startsWith("Ks")){
						String[] str=line.split(" ");
						currentMtl.setSpecularColor(Float.parseFloat(str[1]), Float.parseFloat(str[2]), Float.parseFloat(str[3]));
					}
					else
					if(line.startsWith("Tr") || line.startsWith("d")){
						String[] str=line.split(" ");
						currentMtl.setAlpha(Float.parseFloat(str[1]));
					}
					else
					if(line.startsWith("Ns")){
						String[] str=line.split(" ");
						currentMtl.setShine(Float.parseFloat(str[1]));
					}
					else
					if(line.startsWith("illum")){
						String[] str=line.split(" ");
						currentMtl.setIllum(Integer.parseInt(str[1]));
					}
					else
					if(line.startsWith("map_Ka")){
						String[] str=line.split(" ");
						currentMtl.setTextureFile(str[1]);
					}
				}
				materials.add(currentMtl);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		return materials;
	}
}
