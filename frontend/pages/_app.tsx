import type { AppProps } from "next/app";
import "@/styles/globals.css";
import "@/styles/RecommendedSiders.css";
import "@/styles/mySelect.css";

import Layout from "@/components/Layout/Layout";

export default function App({ Component, pageProps }: AppProps) {
  return (
        <Layout>
          <Component {...pageProps} />
        </Layout>
  );
}
