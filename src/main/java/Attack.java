public class Attack {
    private String ID;
    private String Name;
    private String Category;


    Attack(){

    }

    Attack(String id, String name, String category){
        this.ID=id;
        this.Name=name;
        this.Category=category;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
