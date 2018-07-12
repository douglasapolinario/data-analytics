public enum GrauEsporte {    
	ATLETA("much"), MODERADO("moderate"), CASUAL("little"), SEDENTARIO("nothing");
     
    private final String valor;
    GrauEsporte(String valorOp){
        valor = valorOp;
    }
    public String getValor(){
        return valor;
    }
}