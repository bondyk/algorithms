package com.bondyk.ctci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLEncoding {

    private static final Map<String, Integer> mapping = new HashMap<>();

    static {
        mapping.put("family", 1);
        mapping.put("person", 2);
        mapping.put("firstName", 3);
        mapping.put("lastName", 4);
        mapping.put("state", 5);
    }

    public static void main(String[] args) {
        Element root = new Element("family");
        root.attributes.add(new Attribute("lastName", "McDowel"));
        root.attributes.add(new Attribute("state", "CA"));
        Element child = new Element("person");
        child.text = "Some Message";
        child.attributes.add(new Attribute("firstName", "Gayle"));
        root.children.add(child);
        System.out.println(encode(root));
    }

    private static String encode(Element root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }

    private static void encode(Element element, StringBuilder sb) {
        if (element != null) {
            if (sb.length() != 0) {
                sb.append(" ");
            }

            sb.append(mapping.get(element.name));
            for (Attribute attribute : element.attributes) {
                sb.append(" ").append(mapping.get(attribute.name)).append(" ").append(attribute.value);
            }
            sb.append(" 0"); // END
            for (Element child : element.children) {
                encode(child, sb);
            }
            if (element.text != null) {
                sb.append(" ").append(element.text);
            }
            sb.append(" 0"); // END

        }
    }



    private static class Element {
        private final String name;
        private final List<Attribute> attributes;
        private final List<Element> children;
        private String text;

        private Element(String name) {
            this.name = name;
            this.attributes = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }


    private static class Attribute {
        private final String name;
        private final String value;

        public Attribute(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
