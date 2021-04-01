package com.example.friendmanager;

import android.content.Context;
import android.widget.Button;

public class FriendButton extends androidx.appcompat.widget.AppCompatButton {

    private Friend friend;

    public FriendButton(Context context, Friend newFriend)
    {
        super(context);
        friend = newFriend;
    }

    public String getEmail()
    {
        return friend.getEmail();
    }
}
