import React, { FC, useState } from "react";
import s from "./payment.module.scss";

import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined, CreditCardOutlined } from "@ant-design/icons";

const Payment: FC = () => {
  const [loading, setLoading] = useState(false);

  const onFinish = () => {
    setLoading(true);
    // Perform payment processing logic here
  };

  return (
    <section className={s.payment}>
      <h2>Payment System</h2>
      <Form name="payment-form" onFinish={onFinish}>
        <Form.Item
          name="name"
          rules={[
            {
              required: true,
              message: "Please enter your name",
            },
          ]}
        >
          <Input prefix={<UserOutlined />} placeholder="Name" />
        </Form.Item>

        <Form.Item
          name="cardNumber"
          rules={[
            {
              required: true,
              message: "Please enter your card number",
            },
          ]}
        >
          <Input prefix={<CreditCardOutlined />} placeholder="Card Number" />
        </Form.Item>

        <Form.Item
          name="expiryDate"
          rules={[
            {
              required: true,
              message: "Please enter expiry date",
            },
          ]}
        >
          <Input placeholder="Expiry Date (MM/YY)" />
        </Form.Item>

        <Form.Item
          name="cvv"
          rules={[
            {
              required: true,
              message: "Please enter CVV",
            },
          ]}
        >
          <Input placeholder="CVV" />
        </Form.Item>

        <Form.Item>
          <Button
            className={s.paymentButton}
            type="primary"
            htmlType="submit"
            loading={loading}
          >
            Pay Now
          </Button>
        </Form.Item>
      </Form>
    </section>
  );
};

export default Payment;
