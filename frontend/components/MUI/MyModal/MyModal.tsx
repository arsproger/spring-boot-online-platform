import React, { FC } from "react";

import { Col, Modal, Row } from "antd";
import { useRouter } from "next/router";
import en from "../../../locales/EN/translation.json";
import ru from "../../../locales/RU/translation.json";
import MyButton from "../MyButton/MyButton";

interface MyModalProps {
  isModalOpen: boolean;
  setIsModalOpen: (active: boolean) => void;
}

const MyModal: FC<MyModalProps> = ({ isModalOpen, setIsModalOpen }) => {
  // Функции - для смены текста
  const { locale, push } = useRouter();
  const t = locale === "en" ? en : ru;

  // Функции - для смены url
  const handleClick = (locale: string) => {
    push("/", "/", { locale });
  };

  return (
    <Modal
      title="Выбрать язык"
      open={isModalOpen}
      footer={null}
      onCancel={() => setIsModalOpen(false)}
    >
      <Row justify="center" gutter={[20, 30]} style={{ padding: 10 }}>
        <Col xs={24} sm={12} md={8}>
          <MyButton onClick={() => handleClick("en")} icon={"🇺🇸"} block>
            English
          </MyButton>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <MyButton onClick={() => handleClick("de")} icon={"🇩🇪"} block>
            Deutsch
          </MyButton>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <MyButton onClick={() => handleClick("ru")} icon={"🇷🇺"} block>
            Russia
          </MyButton>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <MyButton onClick={() => handleClick("ch")} icon={"🇨🇳"} block>
            China
          </MyButton>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <MyButton onClick={() => handleClick("fr")} icon={"🇫🇷"} block>
            France
          </MyButton>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <MyButton onClick={() => handleClick("uk")} icon={"🇺🇦"} block>
            Ukraine
          </MyButton>
        </Col>
      </Row>
    </Modal>
  );
};

export default MyModal;
