/**
 * Copyright 2005-2012 Restlet S.A.S.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL
 * 1.0 (the "Licenses"). You can select the license that you prefer but you may
 * not use this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * Routing to annotated server resources.
 */
public class MailServerApplication2 extends Application {

    /**
     * Launches the application with an HTTP server.
     * 
     * @param args
     *            The arguments.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Server mailServer = new Server(Protocol.HTTP, 8111);
        mailServer.setNext(new MailServerApplication2());
        mailServer.start();
    }

    /**
     * Constructor.
     */
    public MailServerApplication2() {
        setName("RESTful Mail Server");
        setDescription("Example for Restlet in Action book");
        setOwner("Restlet S.A.S.");
        setAuthor("The Restlet Team");
    }

    /**
     * Creates a root Router to dispatch call to server resources.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("http://localhost:8111/", 
                RootServerResource.class);
        router.attach("http://localhost:8111/accounts/",
                AccountsServerResource.class);
        router.attach("http://localhost:8111/accounts/{accountId}",
                AccountServerResource.class);
        return router;
    }
}
