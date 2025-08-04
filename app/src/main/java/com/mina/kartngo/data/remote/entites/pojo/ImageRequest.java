package com.mina.kartngo.data.remote.entites.pojo;

import com.google.gson.annotations.SerializedName;

public class ImageRequest {

        @SerializedName("class")
        public String myClass = "sa.com.doit.cart.service.request.ImageRequest";
        public String imageId;
        public String quality = "MED";

        public ImageRequest(String imageId){
            this.imageId = imageId;
        }
}
