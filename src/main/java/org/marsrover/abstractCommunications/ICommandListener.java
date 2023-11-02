package org.marsrover.abstractCommunications;

import java.io.IOException;

public interface ICommandListener {

    public void read(String data) throws IOException;

}
