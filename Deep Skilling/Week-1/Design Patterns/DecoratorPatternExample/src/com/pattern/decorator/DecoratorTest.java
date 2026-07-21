package com.pattern.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Notifier emailAndSmsAndSlack = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier()
                )
        );

        emailAndSmsAndSlack.send("Critical system alert!");
    }
}
