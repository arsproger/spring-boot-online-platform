import React, { useState } from "react";
import styles from "./passwordRecovery.module.scss";

import { Form, Input, Button } from "antd";
import {
  UserOutlined,
  MailOutlined,
  ArrowRightOutlined,
} from "@ant-design/icons";

const PasswordRecovery: React.FC = () => {
  const [loading, setLoading] = useState(false);

  const onFinish = () => {
    setLoading(true);
    // Password recovery logic goes here
  };

  return (
    <div className={styles.passwordRecovery}>
      <h1>Password Recovery</h1>
      <Form name="password-recovery-form" onFinish={onFinish}>
        <Form.Item
          name="email"
          rules={[
            {
              type: "email",
              message: "Please enter a valid email address",
            },
            {
              required: true,
              message: "Please enter your email",
            },
          ]}
        >
          <Input prefix={<MailOutlined />} placeholder="Email" />
        </Form.Item>

        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            className={styles.recoveryButton}
            loading={loading}
          >
            <span>Recover Password</span>
            <ArrowRightOutlined />
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default PasswordRecovery;
