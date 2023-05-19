import React, { useState } from "react";
import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";
import styles from "./signUp.module.scss";

const SignUp: React.FC = () => {
  const [loading, setLoading] = useState(false);

  const onFinish = (values: any) => {
    setLoading(true);
    // Handle sign up logic
  };

  return (
    <div className={styles["sign-up-container"]}>
      <h1>Sign Up</h1>
      <Form name="sign-up-form" onFinish={onFinish}>
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
        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: "Please enter your password",
            },
            {
              min: 6,
              message: "Password must be at least 6 characters long",
            },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Password" />
        </Form.Item>
        <Form.Item
          name="confirmPassword"
          dependencies={["password"]}
          rules={[
            {
              required: true,
              message: "Please confirm your password",
            },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue("password") === value) {
                  return Promise.resolve();
                }
                return Promise.reject(new Error("Passwords do not match"));
              },
            }),
          ]}
        >
          <Input.Password
            prefix={<LockOutlined />}
            placeholder="Confirm Password"
          />
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            loading={loading}
            className={styles["submit-btn"]}
          >
            Sign Up
          </Button>
        </Form.Item>
        <div className={styles["social-btn-container"]}>
          <Button
            className={styles["social-btn"]}
            type="primary"
            icon={<i className="fab fa-google"></i>}
          >
            Google
          </Button>
          <Button
            className={styles["social-btn"]}
            type="primary"
            icon={<i className="fab fa-facebook"></i>}
          >
            Facebook
          </Button>
          <Button
            className={styles["social-btn"]}
            type="primary"
            icon={<i className="fab fa-linkedin"></i>}
          >
            LinkedIn
          </Button>
        </div>
      </Form>
    </div>
  );
};

export default SignUp;
