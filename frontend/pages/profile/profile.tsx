import React, { useEffect, useState } from "react";
import s from "./profile.module.scss";

import Link from "next/link";
import Image from "next/image";
import { useRouter } from "next/router";
import axios from "axios";
import { EditOutlined, LogoutOutlined } from "@ant-design/icons";
import cover from "../../public/cover.png";

import Loading from "../../components/Loading/Loading";
import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";
import avatar from "../../public/design.png";

const Profile = () => {
  // Данные пользователя
  const [userData, setUserData] = useState<any>({
    fullName: "",
    email: "",
    password: "",
  });

  // Состояния - для загрузки
  const [isLoading, setIsLoading] = useState(true);

  // Для - маршутизации
  const { push, locale, pathname } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Выйти из аккаунта
  const signOut = () => {
    push("/");
    localStorage.removeItem("token");
    setUserData({});
  };

  useEffect(() => {
    const token = localStorage.getItem("token") ?? "";
    const parsedToken = token !== "" ? (JSON.parse(token) as string) : "";

    // Отправляет get запрос для получения пользователя
    const getUser = async (): Promise<void> => {
      const BASE_URL = "http://localhost:8080";

      try {
        setIsLoading(false);
        const { data } = await axios.get(BASE_URL + "/user/current", {
          headers: { Authorization: `Bearer ${parsedToken}` },
        });
        console.log(data);

        // Сохраняем данные пользователя
        setUserData(data);
      } catch (error) {
        console.log(error);
      }
      setIsLoading(true);
    };
    getUser();
  }, []);

  return (
    <section className={s.profile}>
      <div className={s.profile__body}>
        <Image className={s.coverFirst} src={cover} alt="cover" />
        <div className={s.coverSecond}></div>
        {isLoading ? (
          <div className={s.profile__content}>
            <span className={s.avatar}>
              <Image src={avatar} alt="avatar" />
            </span>
            <div className={s.container}>
              <div className={s.name}>
                <h2>{userData.fullName}</h2>
                <p>{userData.email}</p>
              </div>

              <Link href={"/editing/editing"}>
                <MyButton
                  background="#03d665"
                  hoverBackground="#7329c2"
                  type="primary"
                  icon={<EditOutlined />}
                >
                  Редактировать
                </MyButton>
              </Link>
            </div>
            <div className={s.text}>
              <p>
                Рыбатекст используется дизайнерами, проектировщиками и
                фронтендерами, когда нужно быстро заполнить макеты или прототипы
                содержимым. Это тестовый контент, который не должен нести
                никакого смысла, лишь показать наличие самого текста или
                продемонстрировать типографику в деле.
              </p>
            </div>

            <MyButton
              background="#03d665"
              hoverBackground="#7329c2"
              type="primary"
              icon={<LogoutOutlined />}
              onClick={signOut}
            >
              Выйти
            </MyButton>
          </div>
        ) : (
          <div className={s.loading}>
            <Loading />
          </div>
        )}
      </div>
    </section>
  );
};

export default Profile;
