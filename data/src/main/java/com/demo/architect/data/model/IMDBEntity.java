package com.demo.architect.data.model;

import java.util.List;

/**
 * Created by admin on 7/6/17.
 */

public class IMDBEntity {
    private List<NameApproxBean> name_approx;

    public List<NameApproxBean> getName_approx() {
        return name_approx;
    }

    public void setName_approx(List<NameApproxBean> name_approx) {
        this.name_approx = name_approx;
    }

    public static class NameApproxBean {
        /**
         * id : nm0004950
         * title :
         * name : Jennifer Garner
         * description : Actress, Dallas Buyers Club
         */

        private String id;
        private String title;
        private String name;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
