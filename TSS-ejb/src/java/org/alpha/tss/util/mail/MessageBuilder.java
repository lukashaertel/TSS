/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.util.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author pazuzu
 */
public class MessageBuilder {

    /**
     * Default encoding for the messages
     */
    public static final String DEFAULT_ENCODING = "utf-8";

    /**
     * Message to be initialized on
     */
    private final MimeMessage message;

    /**
     * The chosen encoding
     */
    private final String encoding;

    /**
     * True if the sent-date has been user defined
     */
    private boolean manualDate;

    /**
     * List of recipients for `To`
     */
    private List<Address> to;

    /**
     * List of recipients for `CC`
     */
    private List<Address> cc;

    /**
     * List of recipients for `BCC`
     */
    private List<Address> bcc;

    /**
     * Assists in setting and finalizing the parameters of a MIME message
     *
     * @param message The existing message to edit
     * @param encoding The message encoding to use
     */
    public MessageBuilder(MimeMessage message, String encoding) {
        this.message = message;
        this.encoding = encoding;

        manualDate = false;

        to = null;
        cc = null;
        bcc = null;
    }

    /**
     * Assists in setting and finalizing the parameters of a MIME message
     *
     * @param message The existing message to edit
     */
    public MessageBuilder(MimeMessage message) {
        this(message, DEFAULT_ENCODING);
    }

    /**
     * Creates a MIME message for the session and assists in setting and
     * finalizing the parameters
     *
     * @param session The session to build on
     * @param encoding The message encoding to use
     */
    public MessageBuilder(Session session, String encoding) {
        this(new MimeMessage(session), encoding);
    }

    /**
     * Creates a MIME message for the session and assists in setting and
     * finalizing the parameters
     *
     * @param session The session to build on
     */
    public MessageBuilder(Session session) {
        this(new MimeMessage(session), DEFAULT_ENCODING);
    }

    /**
     * Static invocation of the constructor
     *
     * @param session The session to use
     * @return returns the constructed message builder
     */
    public static MessageBuilder on(Session session) {
        return new MessageBuilder(session);
    }

    /**
     * Assigns a sender to the mail
     *
     * @param address The sender to use
     * @return Returns the builder itself
     * @throws MessagingException Thrown if setting the sender caused a problem
     */
    public MessageBuilder sender(Address address) throws MessagingException {
        message.setSender(address);
        return this;
    }

    /**
     * Assigns a sender to the mail as strings, parsed by
     * {@link InternetAddress}
     *
     * @param address The sender to use
     * @return Returns the builder itself
     * @throws MessagingException Thrown if setting the sender caused a problem
     * @throws AddressException Thrown if something in the addresses cannot be
     * parsed
     */
    public MessageBuilder sender(String address) throws MessagingException, AddressException {
        InternetAddress[] addresses = InternetAddress.parse(address);
        if (addresses.length != 1)
            throw new IllegalArgumentException("More or less than one address found in argument");

        return sender(addresses[0]);
    }

    /**
     * Assigns a subject to the mail
     *
     * @param subject The subject to use
     * @return Returns the builder itself
     * @throws MessagingException Thrown if setting the subject caused a problem
     */
    public MessageBuilder subject(String subject) throws MessagingException {
        message.setSubject(subject, encoding);
        return this;
    }

    /**
     * Assigns a body to the mail
     *
     * @param text The message body to use
     * @return Returns the builder itself
     * @throws MessagingException Thrown if setting the text caused a problem
     */
    public MessageBuilder text(String text) throws MessagingException {
        message.setText(text, encoding);
        return this;
    }

    /**
     * Assigns a body to the mail as HTML code
     *
     * @param text The message body to use
     * @return Returns the builder itself
     * @throws MessagingException Thrown if setting the text caused a problem
     */
    public MessageBuilder html(String text) throws MessagingException {
        message.setContent(text, "text/html; charset=" + encoding);
        return this;
    }

    /**
     * Assigns a sent-date of the mail
     *
     * @param date The date to use
     * @return Returns the builder itself
     * @throws MessagingException Thrown if setting the date caused a problem
     */
    public MessageBuilder sent(Date date) throws MessagingException {
        message.setSentDate(date);
        manualDate = true;
        return this;
    }

    /**
     * Adds recipients to the message
     *
     * @param addresses The addresses to add to
     * @return Returns the builder itself
     */
    public MessageBuilder to(Address... addresses) {
        ensureTo();
        to.addAll(Arrays.asList(addresses));
        return this;
    }

    /**
     * Adds recipients to the message
     *
     * @param addresses The addresses to add to as strings, parsed by
     * {@link InternetAddress}
     * @return Returns the builder itself
     * @throws AddressException Thrown if something in the addresses cannot be
     * parsed
     */
    public MessageBuilder to(String... addresses) throws AddressException {
        ensureTo();
        for (String address : addresses)
            to.addAll(Arrays.asList(InternetAddress.parse(address, false)));
        return this;
    }

    /**
     * Adds CC recipients to the message
     *
     * @param addresses The addresses to add to
     * @return Returns the builder itself
     */
    public MessageBuilder cc(Address... addresses) {
        ensureCC();
        cc.addAll(Arrays.asList(addresses));
        return this;
    }

    /**
     * Adds CC recipients to the message
     *
     * @param addresses The addresses to add to as strings, parsed by
     * {@link InternetAddress}
     * @return Returns the builder itself
     * @throws AddressException Thrown if something in the addresses cannot be
     * parsed
     */
    public MessageBuilder cc(String... addresses) throws AddressException {
        ensureCC();
        for (String address : addresses)
            cc.addAll(Arrays.asList(InternetAddress.parse(address, false)));
        return this;
    }

    /**
     * Adds BCC recipients to the message
     *
     * @param addresses The addresses to add to
     * @return Returns the builder itself
     */
    public MessageBuilder bcc(Address... addresses) {
        ensureBCC();
        bcc.addAll(Arrays.asList(addresses));
        return this;
    }

    /**
     * Adds BCC recipients to the message
     *
     * @param addresses The addresses to add to as strings, parsed by
     * {@link InternetAddress}
     * @return Returns the builder itself
     * @throws AddressException Thrown if something in the addresses cannot be
     * parsed
     */
    public MessageBuilder bcc(String... addresses) throws AddressException {
        ensureBCC();
        for (String address : addresses)
            bcc.addAll(Arrays.asList(InternetAddress.parse(address, false)));
        return this;
    }

    /**
     * Fills in the remaining fields, then sends the mail via {@link Transport}
     *
     * @throws MessagingException Thrown if setting the remaining fields caused
     * a problem
     */
    public void send() throws MessagingException {
        if (to != null)
            message.addRecipients(Message.RecipientType.TO, to.toArray(new Address[to.size()]));
        if (cc != null)
            message.addRecipients(Message.RecipientType.TO, cc.toArray(new Address[cc.size()]));
        if (bcc != null)
            message.addRecipients(Message.RecipientType.TO, bcc.toArray(new Address[bcc.size()]));

        if (!manualDate)
            message.setSentDate(new Date());

        Transport.send(message);
    }

    /**
     * Ensures that the `To` list exists
     */
    private void ensureTo() {
        to = new ArrayList<>();
    }

    /**
     * Ensures that the `CC` list exists
     */
    private void ensureCC() {
        cc = new ArrayList<>();
    }

    /**
     * Ensures that the `BCC` list exists
     */
    private void ensureBCC() {
        bcc = new ArrayList<>();
    }

}
