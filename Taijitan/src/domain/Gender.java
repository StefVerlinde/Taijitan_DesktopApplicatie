package domain;

public enum  Gender {
    Man(0), Vrouw(1);

    private int id;

    Gender(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public static Gender getById(int id){
        for(Gender g: values()){
            if(g.id == id) return g;
        }
        return null;
    }
}
