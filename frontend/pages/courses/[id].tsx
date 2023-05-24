import { useRouter } from "next/router";

export default function () {
  const { query } = useRouter();

  return <section>Курсы {query.id}</section>;
}
