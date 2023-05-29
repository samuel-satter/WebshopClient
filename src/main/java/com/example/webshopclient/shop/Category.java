package com.example.webshopclient.shop;

public enum Category {
    COMPUTER {
        public String toString() {
            return "computer";
        }
    },
    HOUSE {
      public String toString() {
          return "house";
      }
    },
    OTHER {
        public String toString() {
            return "other";
        }
    }
}
