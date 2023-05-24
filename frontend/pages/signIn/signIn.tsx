import React, { useState } from "react";
import s from "./signIn.module.scss";

import Link from "next/link";
import { useRouter } from "next/router";
import axios, { AxiosResponse } from "axios";
import { Form, Input } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "@/components/MUI/MyButton/MyButton";

const SignIn: React.FC = () => {
  // Состояния - для данных пользователя авторизации
  const [userLogin, setUserLogin] = useState<{
    username: string;
    password: string;
  }>({
    username: "arsenov@gmail.com",
    password: "123456",
  });

  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState<boolean>(false);

  // Для - маршутизации
  const { push, locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос
  const handleSubmit = async (values: any): Promise<void> => {
    console.log(values);
    
    const BASE_URL = "http://localhost:8080";
    try {
      setLoading(true);

      const { data }: AxiosResponse<{ token: string }> = await axios.post(
        BASE_URL + "/auth/login",
        userLogin
      );

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
      setUserLogin({
        username: "",
        password: "",
      });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className={s.signIn}>
      <h2>{t.signIn[0]}</h2>
      <Form name="sign-in-form" onFinish={handleSubmit}>
        <Form.Item
          name="email"
          rules={[
            {
              type: "email",
              message: t.signIn[3],
            },
            {
              required: true,
              message: t.signIn[4],
            },
          ]}
        >
          <Input
            defaultValue={userLogin.username}
            onChange={(e) => {
              setUserLogin({ ...userLogin, username: e.target.value });
            }}
            prefix={<UserOutlined />}
            placeholder={t.signIn[1]}
          />
        </Form.Item>

        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: t.signIn[5],
            },
          ]}
        >
          <Input.Password
            defaultValue={userLogin.password}
            onChange={(e) => {
              setUserLogin({ ...userLogin, password: e.target.value });
            }}
            prefix={<LockOutlined />}
            placeholder={t.signIn[2]}
          />
        </Form.Item>

        <Form.Item name="submit">
          <MyButton
            background="#03d665"
            hoverBackground="#7329c2"
            type="primary"
            htmlType="submit"
            loading={loading}
            onClick={handleSubmit}
          >
            {t.signIn[6]}
          </MyButton>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signIn[7]}
          </a>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signIn[8]}
          </a>
        </Form.Item>

        <Form.Item>
          <Link href="/passwordRecovery/passwordRecovery">{t.signIn[9]}</Link>
        </Form.Item>
      </Form>
    </div>
  );
};

export default SignIn;
