package de.borisskert.sudoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

class CoinTest {

    private Coin coin;

    @BeforeEach
    public void setup() throws Exception {
        coin = Coin.create(79871231232391L);
    }

    @Test
    public void shouldThrowWhenCoinIsNotFlippedAlready() throws Exception {
        try {
            coin.isOnHeads();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is(equalTo("Coin must be flipped")));
        }
    }

    @Nested
    class FlipFirstTime {
        @BeforeEach
        public void setup() throws Exception {
            coin.flip();
        }

        @Test
        public void shouldBeOnTails() throws Exception {
            assertThat(coin.isOnHeads(), is(equalTo(false)));
        }

        @Nested
        class FlipSecondTime {
            @BeforeEach
            public void setup() throws Exception {
                coin.flip();
            }

            @Test
            public void shouldBeOnTails() throws Exception {
                assertThat(coin.isOnHeads(), is(equalTo(false)));
            }

            @Nested
            class FlipThirdTime {
                @BeforeEach
                public void setup() throws Exception {
                    coin.flip();
                }

                @Test
                public void shouldBeOnTails() throws Exception {
                    assertThat(coin.isOnHeads(), is(equalTo(true)));
                }

                @Nested
                class FlipFourthTime {
                    @BeforeEach
                    public void setup() throws Exception {
                        coin.flip();
                    }

                    @Test
                    public void shouldBeOnTails() throws Exception {
                        assertThat(coin.isOnHeads(), is(equalTo(true)));
                    }

                    @Nested
                    class FlipFifthTime {
                        @BeforeEach
                        public void setup() throws Exception {
                            coin.flip();
                        }

                        @Test
                        public void shouldBeOnTails() throws Exception {
                            assertThat(coin.isOnHeads(), is(equalTo(true)));
                        }

                        @Nested
                        class FlipSixthTime {
                            @BeforeEach
                            public void setup() throws Exception {
                                coin.flip();
                            }

                            @Test
                            public void shouldBeOnTails() throws Exception {
                                assertThat(coin.isOnHeads(), is(equalTo(true)));
                            }

                            @Nested
                            class FlipSeventhTime {
                                @BeforeEach
                                public void setup() throws Exception {
                                    coin.flip();
                                }

                                @Test
                                public void shouldBeOnTails() throws Exception {
                                    assertThat(coin.isOnHeads(), is(equalTo(false)));
                                }

                                @Nested
                                class FlipEighthTime {
                                    @BeforeEach
                                    public void setup() throws Exception {
                                        coin.flip();
                                    }

                                    @Test
                                    public void shouldBeOnTails() throws Exception {
                                        assertThat(coin.isOnHeads(), is(equalTo(true)));
                                    }

                                    @Nested
                                    class FlipNinethTime {
                                        @BeforeEach
                                        public void setup() throws Exception {
                                            coin.flip();
                                        }

                                        @Test
                                        public void shouldBeOnTails() throws Exception {
                                            assertThat(coin.isOnHeads(), is(equalTo(true)));
                                        }

                                        @Nested
                                        class FlipTenthTime {
                                            @BeforeEach
                                            public void setup() throws Exception {
                                                coin.flip();
                                            }

                                            @Test
                                            public void shouldBeOnTails() throws Exception {
                                                assertThat(coin.isOnHeads(), is(equalTo(true)));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Nested
    class FiftyFifty {
        @BeforeEach
        public void setup() throws Exception {
            coin = Coin.create(234324323413L);
        }

        @Test
        public void shouldProvideAlmostFiftyFiftyHeadsAndTailsByDefault() throws Exception {
            long headsCount = IntStream.range(0, 10000).mapToObj(i -> {
                coin.flip();
                return coin.isOnHeads();
            })
                    .filter(b -> b)
                    .count();

            assertThat(headsCount, is(equalTo(5002L)));
        }

        @Test
        public void shouldProvideAlmostFiftyFiftyHeadsAndTails() throws Exception {
            long headsCount = IntStream.range(0, 10000).mapToObj(i -> {
                coin.flip();
                return coin.isOnHeads();
            })
                    .filter(b -> b)
                    .count();

            assertThat(headsCount, is(equalTo(5002L)));
        }
    }

    @Nested
    class SixtyForty {
        @BeforeEach
        public void setup() throws Exception {
            coin = Coin.create(234324323413L);
        }

        @Test
        public void shouldProvideAlmostFiftyFiftyHeadsAndTailsByDefault() throws Exception {
            long headsCount = IntStream.range(0, 10000).mapToObj(i -> {
                coin.flip(.6);
                return coin.isOnHeads();
            })
                    .filter(b -> b)
                    .count();

            assertThat(headsCount, is(equalTo(5966L)));
        }

        @Test
        public void shouldOnlyAllowProbabilityUntilOneDotZero() throws Exception {
            try {
                coin.flip(1.0);
                fail("Should throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertThat(e.getMessage(), is(equalTo("Parameter 'probability' must be between 0.0 and 1.0 (both exclusive)")));
            }
        }

        @Test
        public void shouldOnlyAllowProbabilityHigherThanZero() throws Exception {
            try {
                coin.flip(0.0);
                fail("Should throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertThat(e.getMessage(), is(equalTo("Parameter 'probability' must be between 0.0 and 1.0 (both exclusive)")));
            }
        }
    }
}
