package shop.mtcoding.blog.board;


import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String username;

        public Board toEntity() {
            return new Board(this.title, this.content, this.username);
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;
    }
}
