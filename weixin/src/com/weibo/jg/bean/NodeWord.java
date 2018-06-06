package com.weibo.jg.bean;

public class NodeWord
{
    // {category:0, name: '乔布斯', value : 10, label: '乔布斯\n（主要）'},
    int category;// 关系类型

    String name; // 节点名称

    int value; // 节点重要度

    String label;// 主要节点 '乔布斯\n（主要）'

    public int getCategory()
    {
        return category;
    }

    public void setCategory(int category)
    {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
}
