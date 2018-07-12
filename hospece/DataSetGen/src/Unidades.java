
public enum Unidades {    
    SALVAR(1), IMPRMIR(2), ABRIR(3), VISUALIZAR(4), FECHAR(5);
     
    private final int valor;
    Unidades(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}