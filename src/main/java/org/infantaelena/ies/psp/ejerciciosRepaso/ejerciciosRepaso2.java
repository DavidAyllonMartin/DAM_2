package org.infantaelena.ies.psp.ejerciciosRepaso;

public class ejerciciosRepaso2 {

    public static void main(String[] args) {



    }

}

class alumno {

   private String name;
   private double mark;

    public alumno(String name, double mark) {
        setName(name);
        setMark(mark);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
