import React, { useState } from "react";
import s from "./signUp.module.scss";

import { useRouter } from "next/router";
import axios from "axios";
import { AxiosResponse } from "axios";
import { Form, Input } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";

const SignUp: React.FC = () => {
  // Состояния - для данных пользователя регистрации
  const [userRegister, setUserRegister] = useState({
    fullName: "arsenov",
    email: "arsenov@gmail.com",
    password: "123456",
  });

  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState(false);

  // Функция - для загрузки кнопки
  const onFinish = () => {
    setLoading(!loading);
  };

  // Для - маршутизации
  const { push, locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос
  const handleSubmit = async (): Promise<void> => {
    const BASE_URL = "http://localhost:8080";
    try {
      const { data }: AxiosResponse<{ token: string }> = await axios.post(
        BASE_URL + "/auth/register",
        userRegister
      );

      console.log(data.token);

      // Сохраняем токен пользователя
      localStorage.setItem("token", JSON.stringify(data.token));

      // Достаем токен пользователя
      const token = localStorage.getItem("token") ?? "";
      const parsedToken = token !== "" ? (JSON.parse(token) as string) : "";

      // Если есть токен то перенаправляем пользователя на профиль
      if (!!parsedToken) {
        push("/profile/profile");
      }
      // Сбрасываем поля объекта
      setUserRegister({
        fullName: "",
        email: "",
        password: "",
      });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className={s.signUp}>
      <h2>{t.signUp[0]}</h2>
      <Form name="sign-up-form" onFinish={onFinish}>
        <Form.Item
          name="name"
          rules={[
            {
              required: true,
              message: t.signUp[5],
            },
          ]}
        >
          <Input
            defaultValue={userRegister.fullName}
            onChange={(e) => {
              setUserRegister({ ...userRegister, fullName: e.target.value });
            }}
            prefix={<UserOutlined />}
            placeholder={t.signUp[1]}
          />
        </Form.Item>

        <Form.Item
          name="email"
          rules={[
            {
              type: "email",
              message: t.signUp[6],
            },
            {
              required: true,
              message: t.signUp[7],
            },
          ]}
        >
          <Input
            defaultValue={userRegister.email}
            onChange={(e) => {
              setUserRegister({ ...userRegister, email: e.target.value });
            }}
            prefix={<MailOutlined />}
            placeholder={t.signUp[2]}
          />
        </Form.Item>

        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: t.signUp[8],
            },
            {
              min: 6,
              message: t.signUp[9],
            },
          ]}
        >
          <Input.Password
            defaultValue={userRegister.password}
            onChange={(e) => {
              setUserRegister({ ...userRegister, password: e.target.value });
            }}
            prefix={<LockOutlined />}
            placeholder={t.signUp[3]}
          />
        </Form.Item>

        <Form.Item
          name="confirmPassword"
          dependencies={["password"]}
          rules={[
            {
              required: true,
              message: t.signUp[10],
            },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue("password") === value) {
                  return Promise.resolve();
                }
                return Promise.reject(new Error(t.signUp[11]));
              },
            }),
          ]}
        >
          <Input.Password
            defaultValue={userRegister.password}
            prefix={<LockOutlined />}
            placeholder={t.signUp[4]}
          />
        </Form.Item>

        <Form.Item>
          <MyButton
            background="#7329c2"
            hoverBackground="#03d665"
            type="primary"
            loading={loading}
            onClick={handleSubmit}
          >
            {t.signUp[12]}
          </MyButton>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signUp[13]}
          </a>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signUp[14]}
          </a>
        </Form.Item>
      </Form>
    </div>
  );
};

export default SignUp;
