import React, { FC } from "react";

import { Col, Modal, Row } from "antd";
import { useRouter } from "next/router";

import MyButton from "../MUI/MyButton/MyButton";

interface IMyModalProps {
  isModalOpen: boolean;
  setIsModalOpen: (active: boolean) => void;
}

const MyModal: FC<IMyModalProps> = ({ isModalOpen, setIsModalOpen }) => {
  // Функции - для смены текста
  const { push, pathname } = useRouter();

  // Функции - для смены url
  const handleClick = (locale: string) => {
    if (pathname === "/signUp/signUp") {
      push("/signUp/signUp", "/signUp/signUp", { locale });
    } else if (pathname === "/signIn/signIn") {
      push("/signIn/signIn", "/signIn/signIn", { locale });
    } else if (pathname === "/passwordRecovery/passwordRecovery") {
      push(
        "/passwordRecovery/passwordRecovery",
        "/passwordRecovery/passwordRecovery",
        { locale }
      );
    } else if (pathname === "/profile/profile") {
      push("/profile/profile", "/profile/profile", { locale });
    } else {
      push("/", "/", { locale });
    }
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
