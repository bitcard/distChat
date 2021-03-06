package distChat.operation;

import distChat.DistChatConfiguration;
import distChat.model.ChatRoom;
import distChat.model.ChatUser;
import kademlia.exceptions.RoutingException;
import kademlia.operation.Operation;

import java.io.IOException;

// operation which add to operataion queue new OwnerBackupOperation every X ms
public class OwnerBackupSchedulerOperation implements Operation, RepeatOperation {

    private ChatUser me;


    public OwnerBackupSchedulerOperation(ChatUser me) {
        this.me = me;
    }

    @Override
    public void execute() throws IOException, RoutingException {


        if(me.getChatRoomsOwned().keySet().size() > 0)
            me.getOp().queueOperation(new OwnerBackupOperation(me));


    }

    @Override
    public int getInterval() {
        return DistChatConfiguration.OWNER_BACKUP_CHATROOM_OPERATON_INTERVAL;
    }
}
