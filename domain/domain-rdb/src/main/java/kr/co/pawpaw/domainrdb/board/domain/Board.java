package kr.co.pawpaw.domainrdb.board.domain;

import kr.co.pawpaw.domainrdb.common.BaseTimeEntity;
import kr.co.pawpaw.domainrdb.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_Removed = false")
public final class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    private boolean isRemoved = false;

    @Column(name = "liked_count")
    private int likedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(String title, String content, User user, String writer) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.writer = writer;
        this.isRemoved = false;
    }


    public void plusLikedCount(){
        this.likedCount = likedCount +1;
    }
    public void minusLikedCount(){
        this.likedCount = likedCount - 1;
    }


    public void updateTitleAndContent(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void remove() {
        this.isRemoved = true;
    }
}
