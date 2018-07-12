public enum GrauBebe {    
    NAO_BEBE("nothing"), CASUALMENTE("little"), MODERADO("moderate"), DEPENDENTE("much");
     
    private final String valor;
    GrauBebe(String valorOp){
        valor = valorOp;
    }
    public String getValor(){
        return valor;
    }
}