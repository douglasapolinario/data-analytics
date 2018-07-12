import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

	private static Integer dataSet = 327; // QUANTIDADE DE REGISTROS
	private static String dataSetName = "dataset_327"; // NOME DO ARQUIVO
	private static Integer peso_100 = 100;
	private static String separetor = ",";
	private static String new_line = "\n";
	private static Integer seed;
	private static Integer max_idade = 105;
	private static String YES = "yes";
	private static String NO = "no";
	
	private static boolean treino = false; // SE FOR TREINO POSSUI ANOS ENTRE 2014 - 2017 E POSSUI DADOS INFO DE SOBREVIVEU
										  // SE FOR TESTE POSSUI ANO FIXO 2018 E NÃO POSSUI INFO DE SOBREVIVEU

		
	public static void main (String args[]) throws IOException {
				 
		ArrayList<Integer> unidadeList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> anoList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> idadeList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> sexoList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> pesoList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> bebeList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> fumaList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> esporteList = randomIntValueList(seed,dataSet,peso_100);
		
		ArrayList<Integer> paradaRespList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> insufiRespList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> infartMiocList = randomIntValueList(seed,dataSet,peso_100);
		ArrayList<Integer> baixoNivOxList = randomIntValueList(seed,dataSet,peso_100);
		
		ArrayList<Integer> statusList = randomIntValueList(seed,dataSet,peso_100);

		
//		System.out.print("UNIDADE" + separetor);
//		System.out.print("IDADE" + separetor);
//		System.out.print("SEXO" + separetor);
//		System.out.print("PESO" + separetor);
//		System.out.print("GRAU_BEBE" + separetor);
//		System.out.print("GRAU_FUMA" + separetor);
//		System.out.print("GRAU_ESPORTE" + separetor);
//		System.out.print("STATUS" + separetor);
//		System.out.println();
		
		FileWriter arq = new FileWriter("C:\\data\\"+dataSetName+".csv");
		PrintWriter gravarArq = new PrintWriter(arq);
		
		String incHeader = treino ? separetor + "survived"  : "";
		
		gravarArq.printf(	"case"
			+ separetor +	"unity" 
			+ separetor +	"year" 
			+ separetor +	"age" 
			+ separetor + 	"sex" 
			+ separetor + 	"weight" 
			+ separetor + 	"drink_degree" 
			+ separetor +	"smoke_degree" 
			+ separetor +	"sport_degree" 
			+ separetor + 	"respiratory_arrest" 
			+ separetor + 	"respiratory_insufficiency" 
			+ separetor + 	"myocardial_infarction" 
			+ separetor + 	"low_oxygen_saturation" 
			+ incHeader
			+ new_line
				);
		
		
		 for (int i = 0; i < dataSet; i++) {
	
			Integer cont = i + 1;
			String unidade = getUnidade(unidadeList.get(i));
			String ano = treino ? getAno(anoList.get(i)) : "2018";
			String idade = getIdade(idadeList.get(i));
			Integer intIdade = Integer.parseInt(idade);
			String sexo = getSexo(sexoList.get(i));
			Double peso = getPeso(pesoList.get(i), intIdade);
			String bebe = getGrauBebe(bebeList.get(i), intIdade);
			String fuma = getGrauFuma(fumaList.get(i), intIdade); 
			String esporte = getGrauEsporte(esporteList.get(i), intIdade);			
			String sobrevive = getStatus(statusList.get(i));
			String parada = getParadaRespiratoria(paradaRespList.get(i),intIdade,sobrevive);
			String insufi = getInsuficienciaResp(insufiRespList.get(i),intIdade,sobrevive);
			String infarto = getInfartoMiocardio(infartMiocList.get(i),intIdade,sobrevive);
			String nivOxi = getBaixoNivelOxi(baixoNivOxList.get(i),intIdade,sobrevive);
			 
			String incFiller = treino ? separetor + sobrevive : "";
			gravarArq.printf( cont +
						separetor +
						unidade + 
						separetor + 
						ano + 
						separetor + 
						idade + 
						separetor + 
						sexo + 
						separetor + 
						peso +
						separetor + 
						bebe + 
						separetor + 
						fuma + 
						separetor + 
						esporte + 
						separetor + 
						parada + 
						separetor + 
						insufi + 
						separetor + 
						infarto + 
						separetor + 
						nivOxi + 
						incFiller +
						new_line
					);	 
			 
//			System.out.print(getUnidade(unidadeList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getIdade(idadeList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getSexo(sexoList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getPeso(pesoList.get(i), idadeList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getGrauBebe(bebeList.get(i), idadeList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getGrauFuma(fumaList.get(i), idadeList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getGrauEsporte(esporteList.get(i), idadeList.get(i)));
//			System.out.print(separetor);
//			
//			System.out.print(getStatus(statusList.get(i)));
//			
//			System.out.println();
		}
			
		 arq.close();
		 
		 System.out.println("vai curintcHa!");
		
	}
		
	private static String getUnidade(Integer value){
		if(value <= 20) { // 20%
			return "VILLA LOBOS";
		}else if(value <= 40) { // 20%
			return "BUTANTA";
		}else if(value <= 45) { // 05%
			return "PARAISOPOLIS";
		}else if(value <= 50) { // 05%
			return "DOM BOSCO";
		}else if(value <= 55) { // 05%
			return "DOM PEDRO II";
		}else if(value <= 60) { // 05%
			return "PENHA";
		}else if(value <= 70) { // 10%
			return "SAO CAMILO";
		}else if(value <= 80) { // 10%
			return "SANTA RITA";
		}else if(value <= 90) { // 10%
			return "ANALIA FRANCO";
		}else if(value <= 100) { // 10%
			return "ANA ROSA";
		}
		return "?";
	}
	
	private static String getAno(Integer value){
		if(value <= 20) {
			return "2010";
		}else if(value <= 10) {
			return "2011";
		}else if(value <= 20) {
			return "2012";
		}else if(value <= 30) {
			return "2013";
		}else if(value <= 40) {
			return "2014";
		}else if(value <= 55) {
			return "2015";
		}else if(value <= 75) {
			return "2016";
		}else if(value <= 100) {
			return "2017";
		}
		return "-1";
	}
	
	private static String getIdade(Integer value){
		if(value <= 5) {// ENTRE 0 e 5 ANOS | 05%
			return randomIntValue(seed,0,5);
		}else if(value <= 10) { // ENTRE 6 e 10 ANOS | 05%
			return randomIntValue(seed,6,10);
		}else if(value <= 20) { // ENTRE 11 E 20 ANOS | 10%
			return randomIntValue(seed,11,20);
		}else if(value <= 30) { // ENTRE 21 E 30 ANOS | 10%
			return randomIntValue(seed,21,30);
		}else if(value <= 55) { // ENTRE 31 E 45 ANOS | 25%
			return randomIntValue(seed,31,45);
		}else if(value <= 75) { // ENTRE 46 E 60 ANOS | 20%
			return randomIntValue(seed,46,60);
		}else if(value <= 90) { // ENTRE 61 E 80 ANOS | 15%
			return randomIntValue(seed,61,80);
		}else if(value <= 98) { // ENTRE 81 E 90 ANOS | 8%
			return randomIntValue(seed,81,90);
		}else if(value <= 100) { // ENTRE 91 E 105 ANOS | 2%
			return randomIntValue(seed,91,max_idade);
		}
		return "-1";
	}
	
	
	private static String getSexo(Integer value){
		if(value <= 60) { // MASCULINO
			return "male";
		}else if(value <= 100) { // FEMININO
			return "female";
		}
		return "?";
	}
	
	
	private static String getStatus(Integer value){
		if(value <= 40) { 
			return NO;
		}else if(value <= 100) {
			return YES;
		}
		return "?";
	}
	
	
	private static String getStatus(Integer value,Integer gFuma, Integer gBebe, Integer gEsporte, Integer idade, Integer peso){
		if(value <= 40) { 
			return "0";
		}else if(value <= 100) {
			return "1";
		}
		return "?";
	}
	
	private static Double getPeso(Integer value, Integer idade){
		if(idade <= 0) { // 
			return ramdomWeight(2.0, 5.100);
		}else if(idade <= 1) { // 
			return ramdomWeight(5.100, 11.900);
		}else if(idade <= 2) { // 
			return ramdomWeight(12.100, 15.900);
		}else if(idade <= 3) { // 
			return ramdomWeight(13.100, 18.900);
		}else if(idade <= 4) { // 
			return ramdomWeight(14.100, 20.900);
		}else if(idade <= 5) { // 
			return ramdomWeight(15.100, 23.900);
		}else if(idade <= 8) { // 
			return ramdomWeight(20.100, 32.900);
		}else if(idade <= 10) { //
			return ramdomWeight(28.100, 35.900);
		}else if(idade <= 14) { // 
			return ramdomWeight(34.100, 45.900);
		}else if(idade <= 18) { // 
			return ramdomWeight(38.100, 60.900);
		}else if(idade <= 20) { // 
			return ramdomWeight(38.100, 76.900);
		}else if(idade <= max_idade) { // 
			if (value <= 20) {
				return ramdomWeight(50.100, 65.900);
			}else if (value <= 50) {
				return ramdomWeight(60.100, 90.900);
			}else if (value <= 80) {
				return ramdomWeight(85.100, 105.900);
			}else if (value <= 100) {
				return ramdomWeight(90.100, 135.900);
			}
		}
		return -0.0;
	}
	
	private static String getGrauBebe(Integer value, Integer idade){
		// 
		if(idade <= 15) { // 
			return GrauBebe.NAO_BEBE.getValor();
		}else if(idade <= 18) { // 
			if (value <= 80)
				return GrauBebe.NAO_BEBE.getValor();
			else if (value <= 99)
				return GrauBebe.CASUALMENTE.getValor();
			else if (value <= 99)
				return GrauBebe.MODERADO.getValor();
		}else if(idade <= 25) { // 
			if (value <= 40)
				return GrauBebe.NAO_BEBE.getValor();
			else if (value <= 75)
				return GrauBebe.CASUALMENTE.getValor();
			else if (value <= 90)
				return GrauBebe.MODERADO.getValor();
			else if (value <= 100)
				return GrauBebe.DEPENDENTE.getValor();
		}else if(idade <= 40) { // 
			if (value <= 35)
				return GrauBebe.NAO_BEBE.getValor();
			else if (value <= 65)
				return GrauBebe.CASUALMENTE.getValor();
			else if (value <= 85)
				return GrauBebe.MODERADO.getValor();
			else if (value <= 100)
				return GrauBebe.DEPENDENTE.getValor();
		}else if(idade <= 70) { // 
			if (value <= 60)
				return GrauBebe.NAO_BEBE.getValor();
			else if (value <= 80)
				return GrauBebe.CASUALMENTE.getValor();
			else if (value <= 97)
				return GrauBebe.MODERADO.getValor();
			else if (value <= 100)
				return GrauBebe.DEPENDENTE.getValor();
		}else if(idade <= max_idade) { // 
			if (value <= 60)
				return GrauBebe.NAO_BEBE.getValor();
			else if (value <= 80)
				return GrauBebe.CASUALMENTE.getValor();
			else if (value <= 99)
				return GrauBebe.MODERADO.getValor();
			else if (value <= 100)
				return GrauBebe.DEPENDENTE.getValor();
		}
		
		return "?";
	}
	
	private static String getGrauFuma(Integer value, Integer idade){
		// 
		if(idade <= 15) { // 
			return GrauFuma.NAO_FUMA.getValor();
		}else if(idade <= 18) { // 
			if (value <= 80)
				return GrauFuma.NAO_FUMA.getValor();
			else if (value <= 99)
				return GrauFuma.CASUALMENTE.getValor();
			else if (value <= 99)
				return GrauFuma.MODERADO.getValor();
		}else if(idade <= 25) { // 
			if (value <= 40)
				return GrauFuma.NAO_FUMA.getValor();
			else if (value <= 75)
				return GrauFuma.CASUALMENTE.getValor();
			else if (value <= 90)
				return GrauFuma.MODERADO.getValor();
			else if (value <= 100)
				return GrauFuma.DEPENDENTE.getValor();
		}else if(idade <= 40) { // 
			if (value <= 35)
				return GrauFuma.NAO_FUMA.getValor();
			else if (value <= 65)
				return GrauFuma.CASUALMENTE.getValor();
			else if (value <= 85)
				return GrauFuma.MODERADO.getValor();
			else if (value <= 100)
				return GrauFuma.DEPENDENTE.getValor();
		}else if(idade <= 70) { // 
			if (value <= 60)
				return GrauFuma.NAO_FUMA.getValor();
			else if (value <= 80)
				return GrauFuma.CASUALMENTE.getValor();
			else if (value <= 97)
				return GrauFuma.MODERADO.getValor();
			else if (value <= 100)
				return GrauFuma.DEPENDENTE.getValor();
		}else if(idade <= max_idade) { // 
			if (value <= 60)
				return GrauFuma.NAO_FUMA.getValor();
			else if (value <= 80)
				return GrauFuma.CASUALMENTE.getValor();
			else if (value <= 99)
				return GrauFuma.MODERADO.getValor();
			else if (value <= 100)
				return GrauFuma.DEPENDENTE.getValor();
		}
		
		return "?";
	}
	
	private static String getGrauEsporte(Integer value, Integer idade){
		// 
		if(idade <= 8) { // 
			if (value <= 80)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= 15) { // 
			if (value <= 5)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 65)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 75)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= 20) { // 
			if (value <= 6)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 40)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 70)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= 30) { // 
			if (value <= 6)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 30)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 65)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= 45) { // 
			if (value <= 5)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 30)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 60)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= 60) { // 
			if (value <= 3)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 25)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 55)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= 80) { // 
			if (value <= 0)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 5)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 15)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}else if(idade <= max_idade) { // 
			if (value <= 0)
				return GrauEsporte.ATLETA.getValor();
			else if (value <= 5)
				return GrauEsporte.MODERADO.getValor();
			else if (value <= 10)
				return GrauEsporte.CASUAL.getValor();
			else if (value <= 100)
				return GrauEsporte.SEDENTARIO.getValor();
		}
		
		return "?";
	}
	
	private static String getParadaRespiratoria(Integer value, Integer idade, String status){
		// 
		if(idade <= 15) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 70) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 20) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= 60) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 80) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 40) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= max_idade) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 90) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 60) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}
		
		return "?";
	}
	
	private static String getInsuficienciaResp(Integer value, Integer idade, String status){
		// 
		if(idade <= 15) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 70) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 20) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= 60) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 80) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 40) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= max_idade) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 90) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 60) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}
		
		return "?";
	}
	
	private static String getInfartoMiocardio(Integer value, Integer idade, String status){
		// 
		if(idade <= 15) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 60) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 20) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= 60) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 60) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 40) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= max_idade) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 75) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 50) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}
		
		return "?";
	}
		
	private static String getBaixoNivelOxi(Integer value, Integer idade, String status){
		// 
		if(idade <= 15) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 60) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 20) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= 60) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 60) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 30) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}else if(idade <= max_idade) { 
			if (status.equalsIgnoreCase(NO)) {
				if (value <= 70) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}	
			}else if (status.equalsIgnoreCase(YES)) {
				if (value <= 50) {
					return NO;
				}else if (value <= 100) {
					return YES;
				}
			}
		}
		
		return "?";
	}
	
	private static ArrayList<Integer> randomIntValueList(Integer seed, Integer inter, Integer maxValue) {
		Random gerador;
		if (seed == null)
			gerador = new Random();
		else
			gerador = new Random(seed);
		
		ArrayList<Integer> list = new ArrayList<>();
		
		 for (int i = 0; i < inter; i++) {
	           list.add(gerador.nextInt(maxValue)); 
	     }
		 return list;
	}
	
	private static Double ramdomWeight(Double minValue, Double maxValue) {
		Integer kg = Integer.valueOf(randomIntValue(null,minValue.intValue(),maxValue.intValue()));
		Double gr = ((double) Integer.valueOf(randomIntValue(null,0,9)) / 10);
		Double peso = (kg + gr);
		if (peso >= minValue && peso <= maxValue)
			return peso;
		else
			return (double) kg;
	}
	
	private static String randomIntValue(Integer seed, Integer minValue, Integer maxValue) {
		Random gerador;
		if (seed == null)
			gerador = new Random();
		else
			gerador = new Random(seed);
	    return String.valueOf(gerador.nextInt((maxValue - minValue) + 1) + minValue);
	}

}
