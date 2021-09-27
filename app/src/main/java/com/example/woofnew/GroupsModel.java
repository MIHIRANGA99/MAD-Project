package com.example.woofnew;

public class GroupsModel {

    String Name;
    String Image;
    String Details;
    String GroupId;

    public GroupsModel()
    {
    }

    public GroupsModel(String name, String image, String details, String groupId) {
        Name = name;
        Image = image;
        Details = details;
        GroupId = groupId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }
}
