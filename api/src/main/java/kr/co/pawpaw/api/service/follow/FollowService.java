package kr.co.pawpaw.api.service.follow;

import kr.co.pawpaw.common.exception.user.NotFoundUserException;
import kr.co.pawpaw.domainrdb.follow.domain.Follow;
import kr.co.pawpaw.domainrdb.follow.service.command.FollowCommand;
import kr.co.pawpaw.domainrdb.follow.service.query.FollowQuery;
import kr.co.pawpaw.domainrdb.user.domain.User;
import kr.co.pawpaw.domainrdb.user.domain.UserId;
import kr.co.pawpaw.domainrdb.user.service.query.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    
    private final UserQuery userQuery;
    private final FollowQuery followQuery;
    private final FollowCommand followCommand;
    
    public Follow addFollowing(UserId userId, String toUserId){
        User user = userQuery.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        String fromUserId = user.getUserId().toString();

        if(!followQuery.findByFromUserIdAndToUserId(fromUserId,toUserId).isEmpty()) {
            throw new RuntimeException("이미 팔로우한 계정입니다.");
        }

        Follow follow = Follow.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();
            return followCommand.save(follow);
        }
    }

