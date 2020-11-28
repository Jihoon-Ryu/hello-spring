package hello.hellospring.domain;

public class Member {
    //속성
    private Long id;
    private String name;

    //기능

       //getter setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
