package Entidades;

public class Categoria {
    private String Açougue;
    private String Alimentos_Básicos;
    private String Bebês_e_Crianças;
    private String Bebidas;
    private String Biscoitos_e_Snacks;
    private String Congelados;
    private String Doces_e_Sobremesas;
    private String Feira;
    private String Higiene_e_Perfumaria;
    private String Limpeza;
    private String Massas;
    private String Naturais_e_Funcionais;
    private String Orgânicos;
    private String Padaria;
    private String Peixaria;
    
       
    // get e setters ; object
    
    //Comparação de objetos
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != Categoria.class) {
            return false;
        }
        Categoria c = (Categoria) o;
        
        return (this.Açougue.equals(c.Açougue) && this.Alimentos_Básicos.equals(c.Alimentos_Básicos) && this.Bebidas.equals(c.Bebidas)
                && this.Bebês_e_Crianças.equals(c.Bebês_e_Crianças) && this.Biscoitos_e_Snacks.equals(c.Biscoitos_e_Snacks) && 
                this.Congelados.equals(c.Congelados) && this.Doces_e_Sobremesas.equals(c.Doces_e_Sobremesas) && this.Feira.equals(c.Feira) &&
                this.Higiene_e_Perfumaria.equals(c.Higiene_e_Perfumaria) && this.Limpeza.equals(c.Limpeza) && this.Massas.equals(c.Massas) &&
                this.Orgânicos.equals(c.Orgânicos) && this.Padaria.equals(c.Padaria) && this.Peixaria.equals(c.Peixaria));
    }
    

    public String getAçougue() {
        return Açougue;
    }

    public void setAçougue(String Açougue) {
        this.Açougue = Açougue;
    }

    public String getAlimentos_Básicos() {
        return Alimentos_Básicos;
    }

    public void setAlimentos_Básicos(String Alimentos_Básicos) {
        this.Alimentos_Básicos = Alimentos_Básicos;
    }

    public String getBebês_e_Crianças() {
        return Bebês_e_Crianças;
    }

    public void setBebês_e_Crianças(String Bebês_e_Crianças) {
        this.Bebês_e_Crianças = Bebês_e_Crianças;
    }

    public String getBebidas() {
        return Bebidas;
    }

    public void setBebidas(String Bebidas) {
        this.Bebidas = Bebidas;
    }

    public String getBiscoitos_e_Snacks() {
        return Biscoitos_e_Snacks;
    }

    public void setBiscoitos_e_Snacks(String Biscoitos_e_Snacks) {
        this.Biscoitos_e_Snacks = Biscoitos_e_Snacks;
    }

    public String getCongelados() {
        return Congelados;
    }

    public void setCongelados(String Congelados) {
        this.Congelados = Congelados;
    }

    public String getDoces_e_Sobremesas() {
        return Doces_e_Sobremesas;
    }

    public void setDoces_e_Sobremesas(String Doces_e_Sobremesas) {
        this.Doces_e_Sobremesas = Doces_e_Sobremesas;
    }

    public String getFeira() {
        return Feira;
    }

    public void setFeira(String Feira) {
        this.Feira = Feira;
    }

    public String getHigiene_e_Perfumaria() {
        return Higiene_e_Perfumaria;
    }

    public void setHigiene_e_Perfumaria(String Higiene_e_Perfumaria) {
        this.Higiene_e_Perfumaria = Higiene_e_Perfumaria;
    }

    public String getLimpeza() {
        return Limpeza;
    }

    public void setLimpeza(String Limpeza) {
        this.Limpeza = Limpeza;
    }

    public String getMassas() {
        return Massas;
    }

    public void setMassas(String Massas) {
        this.Massas = Massas;
    }

    public String getNaturais_e_Funcionais() {
        return Naturais_e_Funcionais;
    }

    public void setNaturais_e_Funcionais(String Naturais_e_Funcionais) {
        this.Naturais_e_Funcionais = Naturais_e_Funcionais;
    }

    public String getOrgânicos() {
        return Orgânicos;
    }

    public void setOrgânicos(String Orgânicos) {
        this.Orgânicos = Orgânicos;
    }

    public String getPadaria() {
        return Padaria;
    }

    public void setPadaria(String Padaria) {
        this.Padaria = Padaria;
    }

    public String getPeixaria() {
        return Peixaria;
    }

    public void setPeixaria(String Peixaria) {
        this.Peixaria = Peixaria;
    }
    
    
}
