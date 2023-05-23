import React, { useState } from "react";
import s from "./passwordRecovery.module.scss";

import { useRouter } from "next/router";
import { Form, Input, Button } from "antd";
import { MailOutlined, ArrowRightOutlined } from "@ant-design/icons";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";

const PasswordRecovery: React.FC = () => {
  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState(false);

  // Функция - для загрузки кнопки
  const onFinish = () => {
    setLoading(true);
  };

  // Для - маршутизации
  const { push, locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос для восстановления пароля
  const handleSubmit = async (): Promise<void> => {};

  return (
    <div className={s.passwordRecovery}>
      <h1>{t.passwordRecovery[0]}</h1>
      <Form name="password-recovery-form" onFinish={onFinish}>
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
            prefix={<MailOutlined />}
            placeholder={t.passwordRecovery[1]}
          />
        </Form.Item>

        <Form.Item>
          <MyButton
            type="primary"
            className={s.passwordRecoveryButton}
            loading={loading}
            onClick={handleSubmit}
          >
            <span>{t.passwordRecovery[4]}</span>
            <ArrowRightOutlined />
          </MyButton>
        </Form.Item>
      </Form>
    </div>
  );
};

export default PasswordRecovery;
