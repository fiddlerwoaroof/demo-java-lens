package com.company.main;

import java.util.function.Function;

public class Main {

    public static class Person {
        private String name_;
        public static final IdLens<String, Person> _name = (rec, fun) ->
                new Person(fun.apply(rec.name_), rec.status_);
        public final IdBound<String, Person> name;


        private String status_;
        public static final IdLens<String, Person> statusLens = (rec, fun) ->
                new Person(rec.name_, fun.apply(rec.status_));
        public final IdBound<String, Person> status;

        public Person(String name, String status) {
            name_ = name;
            status_ = status;
            this.name = new IdBound<>(_name, this);
            this.status = new IdBound<>(statusLens, this);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name_='" + name_ + '\'' +
                    ", status_='" + status_ + '\'' +
                    '}';
        }
    }

    public static class Relationship {
        private Person me_;
        public static final IdLens<Person, Relationship> meLens = (Relationship rec, Function<Person, Person> xform) ->
                new Relationship(xform.apply(rec.me_), rec.you_, rec.description_);
        public final IdBound<Person, Relationship> me = new IdBound<>(meLens, this);

        private Person you_;
        public static final IdLens<Person, Relationship> youLens = (Relationship rec, Function<Person, Person> xform) ->
                new Relationship(rec.me_, xform.apply(rec.you_), rec.description_);
        public final IdBound<Person, Relationship> you = new IdBound<>(youLens, this);

        private String description_;
        public static final IdLens<String, Relationship> descriptionLens = (Relationship rec, Function<String, String> xform) ->
                new Relationship(rec.me_, rec.you_, xform.apply(rec.description_));
        public final IdBound<String, Relationship> description = new IdBound<>(descriptionLens, this);

        public Relationship(Person me, Person you, String description) {
            me_ = me;
            you_ = you;
            description_ = description;
        }

        @Override
        public String toString() {
            return "Relationship{" +
                    "me_=" + me_ +
                    ", you_=" + you_ +
                    ", description_='" + description_ + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
      Person a = new Person("name", "hi");

      Person renamed_person = a.name.set("new name");
      renamed_person.name.get();
    }

}
