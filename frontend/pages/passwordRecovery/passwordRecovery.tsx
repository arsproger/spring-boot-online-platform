import React, { FC, useEffect, useState } from "react";
import s from "./passwordRecovery.module.scss";

import { useRouter } from "next/router";
import { Form, Input } from "antd";
import { MailOutlined, ArrowRightOutlined } from "@ant-design/icons";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";
import axios, { AxiosResponse } from "axios";

interface IPasswordRecovery {
  email: string;
}

const PasswordRecovery: FC = () => {
  // Состояния - для данных пользователя регистрации
  const [passwordRecovery, setPasswordRecovery] = useState<IPasswordRecovery>({
    email: "arsproger@gmail.com",
  });
  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState(false);

  // Для - маршутизации
  const { locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос для восстановления пароля
  const handleSubmit = async (value: IPasswordRecovery): Promise<void> => {
    console.log(value);

    setLoading(true);
    const BASE_URL = "http://localhost:8080";
    const params = {
      param1: value,
    };

    try {
      const res = await axios.post(BASE_URL + `/password/reset?email=arsproger@gmail.com`);
      // Сбрасываем поля объекта
      setPasswordRecovery({
        email: "",
      });
    } catch (error) {
      console.log(error);
    }
    setLoading(false);
  };

  // Для сохранения значений
  const [form] = Form.useForm();

  useEffect(() => {
    form.setFieldsValue({ ...passwordRecovery });
  }, []);

  return (
    <section className={s.passwordRecovery}>
      <h2>{t.passwordRecovery[0]}</h2>
      <Form form={form} name="password-recovery-form" onFinish={handleSubmit}>
        <Form.Item
          name="email"
          rules={[
            {
              type: "email",
              message: t.passwordRecovery[2],
            },
            {
              required: true,
              message: t.passwordRecovery[3],
            },
          ]}
        >
          <Input
            className={s.passwordRecovery__input}
            prefix={<MailOutlined />}
            placeholder={t.passwordRecovery[1]}
          />
        </Form.Item>

        <Form.Item>
          <MyButton
            type="primary"
            className={s.passwordRecovery__button}
            loading={loading}
          >
            <span>{t.passwordRecovery[4]}</span>
            <ArrowRightOutlined />
          </MyButton>
        </Form.Item>
      </Form>
    </section>
  );
};

export default PasswordRecovery;
