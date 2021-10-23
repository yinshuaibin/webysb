package com.ysb.test.builder;

/**
 * @author yinshuaibin
 * @date 2021/4/12 11:24
 * @description
 */
public class BuilderDemo {

    private int notNecessary;

    private int notNecessary2;

    private String necessary;

    private String necessary2;

    public static class Builder{

        private int notNecessary;

        private int notNecessary2;

        private final String finalNecessary;

        private final String finalNecessary2;

        public Builder(String finalNecessary, String finalNecessary2){
            this.finalNecessary = finalNecessary;
            this.finalNecessary2 = finalNecessary2;
        }

        public BuilderDemo build(){
            return new BuilderDemo(this);
        }

        public Builder notNecessary(int notNecessary){
            this.notNecessary = notNecessary;
            return this;
        }

        public Builder notNecessary2(int notNecessary2){
            this.notNecessary2 = notNecessary2;
            return this;
        }

    }

    private BuilderDemo(Builder builder){
        this.notNecessary = builder.notNecessary;
        this.notNecessary2 = builder.notNecessary2;
        this.necessary = builder.finalNecessary;
        this.necessary2 = builder.finalNecessary2;
    }

    @Override
    public String toString() {
        return "BuilderDemo{" +
                "notNecessary=" + notNecessary +
                ", notNecessary2=" + notNecessary2 +
                ", necessary='" + necessary + '\'' +
                ", necessary2='" + necessary2 + '\'' +
                '}';
    }
}
