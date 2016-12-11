package objectlist;

import com.example.thanhnguyentung95.cloudfarm.R;

import java.io.Serializable;

/**
 * Created by thanhnguyentung95 on 03/12/2016.
 */

public class Station extends Node implements Serializable{

    public enum Type {FLOWER, FRUIT, TREE};

    private Type type;

    private String name;

    private String desc; // Description

    public Station(){

    }

    public Station(Type type, String name, String desc){
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAssociatedDrawable(){
        return typeToDrawable(type);
    }

    public static int typeToDrawable(Type type){
        switch(type){
            case FLOWER:
                return R.drawable.flower;
            case FRUIT:
                return R.drawable.fruit;
            case TREE:
                return R.drawable.tree;
            default:
                return R.drawable.flower;
        }
    }

    public static Type stringToType(String s){
        switch (s){
            case "flower":
                return Type.FLOWER;
            case "fruit":
                return Type.FRUIT;
            case "tree":
                return Type.TREE;
        }
        return Type.FLOWER;
    }

    public static String typeToString(Type type){
        switch(type){
            case FLOWER:
                return "flower";
            case FRUIT:
                return "fruit";
            case TREE:
                return "tree";
        }
        return "flower";
    }

}
