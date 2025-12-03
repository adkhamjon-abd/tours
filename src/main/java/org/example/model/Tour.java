package org.example.model;

public class Tour {
    private int id;
    private String name;
    private int companyId;  // link to the Company

    private int viewCount;

    public Tour() {}

    public Tour(int id, String name, int companyId, int viewCount) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.viewCount = viewCount;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
}

