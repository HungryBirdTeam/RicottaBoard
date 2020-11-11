package com.websocket.board.model.video;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Video implements Serializable{
  private String id;
  private String vdId;
  private String userEmail;
  private String userNickname;
  private String left;
  private String top;
  private Boolean isHidden;
}
